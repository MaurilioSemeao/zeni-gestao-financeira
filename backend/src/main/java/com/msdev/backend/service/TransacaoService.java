package com.msdev.backend.service;

import com.msdev.backend.dto.request.TransacaoRequest;
import com.msdev.backend.dto.response.TransacaoResponse;
import com.msdev.backend.entity.*;
import com.msdev.backend.enums.StatusExtratoMensal;
import com.msdev.backend.exception.BusinessException;
import com.msdev.backend.exception.RecursoNaoEncontradoException;
import com.msdev.backend.exception.transacao.TransacaoInvalidaException;
import com.msdev.backend.repository.*;
import com.msdev.backend.security.service.AuthenticationService;
import com.msdev.backend.utils.TransacaoMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;


@Service
public class TransacaoService extends BaseServiceImpl<TransacaoEntity, Long, TransacaoRequest, TransacaoResponse> {

    private final TransacaoRepository transacaoRepository;
    private final CartaoRepository cartaoRepository;
    private final UsuarioRepository usuarioRepository;

    private final TransacaoMapper transacaoMapper;
    private final ContaRepository contaRepository;
    private final CarteiraRepository carteiraRepository;
    private final AuthenticationService authenticationService;
    private final ExtratoMensalRepository extratoMensalRepository;
    private final CategoriaRepository categoriaRepository;
    private final ApplicationArguments applicationArguments;

    public TransacaoService(TransacaoRepository transacaoRepository,
                            CartaoRepository cartaoRepository,
                            UsuarioRepository usuarioRepository,
                            TransacaoMapper transacaoMapper, ContaRepository contaRepository, CarteiraRepository carteiraRepository, AuthenticationService authenticationService, ExtratoMensalRepository extratoMensalRepository, CategoriaRepository categoriaRepository, ApplicationArguments applicationArguments){
        super(transacaoRepository, transacaoMapper, "Transação");
        this.transacaoRepository = transacaoRepository;
        this.cartaoRepository = cartaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.transacaoMapper = transacaoMapper;
        this.contaRepository = contaRepository;
        this.carteiraRepository = carteiraRepository;
        this.authenticationService = authenticationService;
        this.extratoMensalRepository = extratoMensalRepository;
        this.categoriaRepository = categoriaRepository;
        this.applicationArguments = applicationArguments;
    }

    @Override
    protected List<TransacaoEntity> fetchAllEntities(){
        UsuarioEntity usuario = authenticationService.getLoggedIUser();
        return  transacaoRepository.findAllByUsuarioId(usuario.getId());
    }

    @Override
    public void beforeCreate (TransacaoEntity entity, TransacaoRequest request){

        UsuarioEntity usuarioLogado = authenticationService.getLoggedIUser();

        UsuarioEntity usuario = usuarioRepository.findById(usuarioLogado.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));

        YearMonth mesReferencia = YearMonth.now();
        ExtratoMensalEntity extrato = extratoMensalRepository.findByUsuarioIdAndMesReferencia(usuario.getId(), mesReferencia)
                        .orElseGet(
                                () ->{
                                    ExtratoMensalEntity novoExtrato = ExtratoMensalEntity.criaExtratoDoMes(usuario, mesReferencia);
                                    return extratoMensalRepository.save(novoExtrato);
                                }
                        );
        if(extrato.getStatus() == StatusExtratoMensal.FECHADA){
            throw new BusinessException("Não é possível adicionar transação a uma extrato fechado");
        }

        entity.setExtrato(extrato);
        entity.setUsuario(usuario);
        CategoriaEntity categoria = categoriaRepository.findByIdAndUsuarioId(request.getCategoriaId(), usuario.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria nãp encontrada"));
        entity.setCategoria(categoria);
        entity.setDataTransacao(LocalDateTime.now());

        switch (request.getMeioPagamento()){
            case CREDITO ->{
                if(request.getCartaoId() == null){
                    throw new TransacaoInvalidaException("Cartão deve ser informado para transação.");
                }
                CartaoEntity cartao = cartaoRepository.findById(request.getCartaoId())
                        .orElseThrow(() -> new RecursoNaoEncontradoException("Cartão não encontrado."));
                BigDecimal gastoCartao = cartao.getGastos().add(request.getValor());
                cartao.setGastos(gastoCartao);
                cartao.setQuantidadeCompras(cartao.getQuantidadeCompras() + 1);
                entity.setCartao(cartao);
            }

            case DEBITO, PIX ->{
                if(request.getContaId() == null){
                    throw new TransacaoInvalidaException("Conta deve ser informado para transação.");
                }
                ContaEntity conta =  contaRepository.findById(request.getContaId())
                        .orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada."));
                entity.setConta(conta);
            }
            case DINHEIRO ->{
                if(request.getCarteiraId() == null){
                    throw new TransacaoInvalidaException("Carteira deve ser informado para transação.");
                }
                CarteiraEntity carteira = carteiraRepository.findById(request.getCarteiraId())
                        .orElseThrow(()-> new RecursoNaoEncontradoException("Carteira não encontrada."));
                entity.setCarteira(carteira);
            }

        }

        BigDecimal novoValor = extrato.getSaldoExtrato().add(entity.getValor());
        extrato.setSaldoExtrato(novoValor);

        extratoMensalRepository.save(extrato);

    }


    @Override
    public void beforeDelete(TransacaoEntity entity) {

        UsuarioEntity usuarioLogado = authenticationService.getLoggedIUser();

        UsuarioEntity usuario = usuarioRepository.findById(usuarioLogado.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));

        YearMonth mesReferencia = YearMonth.now();
        ExtratoMensalEntity extrato = extratoMensalRepository.findByUsuarioIdAndMesReferencia(usuario.getId(), mesReferencia)
                .orElseGet(
                        () ->{
                            ExtratoMensalEntity novoExtrato = ExtratoMensalEntity.criaExtratoDoMes(usuario, mesReferencia);
                            return extratoMensalRepository.save(novoExtrato);
                        }
                );
        if(extrato.getStatus() == StatusExtratoMensal.FECHADA){
            throw new BusinessException("Não é possível remover transação de uma extrato fechado");
        }

        BigDecimal novoValorExtrato = extrato.getSaldoExtrato().subtract(entity.getValor());
        extrato.setSaldoExtrato(novoValorExtrato);

        switch (entity.getMeioPagamento()){
            case CREDITO ->{
                CartaoEntity cartao = entity.getCartao();
                BigDecimal novoValor = cartao.getGastos().subtract(entity.getValor());
                cartao.setGastos(novoValor);
                cartao.setQuantidadeCompras(cartao.getQuantidadeCompras()-1);
            }

            case DEBITO, PIX ->{

            }
            case DINHEIRO ->{

            }

        }
    }

    public List<TransacaoResponse> findByExtratoMensalByUsuarioId(Long extratoId){
        UsuarioEntity usuarioLogado = authenticationService.getLoggedIUser();
        List<TransacaoEntity> transacoes = transacaoRepository.findByExtrato_Usuario_IdAndExtrato_Id(usuarioLogado.getId(), extratoId);
        return transacaoMapper.toResponseList(transacoes);

    }


}
