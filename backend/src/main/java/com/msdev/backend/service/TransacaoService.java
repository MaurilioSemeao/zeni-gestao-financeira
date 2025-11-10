package com.msdev.backend.service;

import com.msdev.backend.dto.request.TransacaoRequest;
import com.msdev.backend.dto.response.TransacaoResponse;
import com.msdev.backend.entity.*;
import com.msdev.backend.exception.RecursoNaoEncontradoException;
import com.msdev.backend.exception.transacao.TransacaoInvalidaException;
import com.msdev.backend.repository.*;
import com.msdev.backend.utils.TransacaoMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TransacaoService extends BaseServiceImpl<TransacaoEntity, Long, TransacaoRequest, TransacaoResponse> {

    private final TransacaoRepository transacaoRepository;
    private final CartaoRepository cartaoRepository;
    private final UsuarioRepository usuarioRepository;

    private final TransacaoMapper transacaoMapper;
    private final ContaRepository contaRepository;
    private final CarteiraRepository carteiraRepository;

    public TransacaoService(TransacaoRepository transacaoRepository,
                            CartaoRepository cartaoRepository,
                            UsuarioRepository usuarioRepository,
                            TransacaoMapper transacaoMapper, ContaRepository contaRepository, CarteiraRepository carteiraRepository){
        super(transacaoRepository, transacaoMapper, "Transação");
        this.transacaoRepository = transacaoRepository;
        this.cartaoRepository = cartaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.transacaoMapper = transacaoMapper;
        this.contaRepository = contaRepository;
        this.carteiraRepository = carteiraRepository;
    }

    @Override
    public TransacaoResponse create(TransacaoRequest request){
        TransacaoEntity entity = transacaoMapper.toEntity(request);

        UsuarioEntity usuario = usuarioRepository.findById(1L)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));

        entity.setUsuario(usuario);

        entity.setDataTransacao(LocalDateTime.now());

        switch (request.getMeioPagamento()){
            case CREDITO ->{
                if(request.getCartaoId() == null){
                    throw new TransacaoInvalidaException("Cartão deve ser informado para transação.");
                }
                CartaoEntity cartao = cartaoRepository.findById(request.getCartaoId())
                        .orElseThrow(() -> new RecursoNaoEncontradoException("Cartão não encontrado."));
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

        TransacaoEntity save = transacaoRepository.save(entity);
        return transacaoMapper.toResponse(save);
    }


}
