package com.msdev.backend.service;

import com.msdev.backend.dto.request.TransacaoRequest;
import com.msdev.backend.dto.response.TransacaoResponse;
import com.msdev.backend.dto.response.UsuarioResponse;
import com.msdev.backend.entity.CartaoEntity;
import com.msdev.backend.entity.ContaEntity;
import com.msdev.backend.entity.TransacaoEntity;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.exception.RecursoNaoEncontradoException;
import com.msdev.backend.exception.transacao.TransacaoInvalidaException;
import com.msdev.backend.repository.CartaoRepository;
import com.msdev.backend.repository.ContaRepository;
import com.msdev.backend.repository.TransacaoRepository;
import com.msdev.backend.repository.UsuarioRepository;
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

    public TransacaoService(TransacaoRepository transacaoRepository,
                            CartaoRepository cartaoRepository,
                            UsuarioRepository usuarioRepository,
                            TransacaoMapper transacaoMapper, ContaRepository contaRepository){
        super(transacaoRepository, transacaoMapper, "Transação");
        this.transacaoRepository = transacaoRepository;
        this.cartaoRepository = cartaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.transacaoMapper = transacaoMapper;
        this.contaRepository = contaRepository;
    }

    @Override
    public TransacaoResponse create(TransacaoRequest request){
        TransacaoEntity entity = transacaoMapper.toEntity(request);

        UsuarioEntity usuario = usuarioRepository.findById(1L)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));

        entity.setUsuario(usuario);

        entity.setDataTransacao(LocalDateTime.now());

        switch (request.getMeioPagamento()){
            case CARTAO ->{
                if(request.getCartaoId() == null){
                    throw new TransacaoInvalidaException("Cartão deve ser informado para transação.");
                }
                CartaoEntity cartao = cartaoRepository.findById(request.getCartaoId())
                        .orElseThrow(() -> new RecursoNaoEncontradoException("Cartão não encontrado."));
                entity.setCartao(cartao);
            }

            case CONTA ->{
                if(request.getContaId() == null){
                    throw new TransacaoInvalidaException("Conta deve ser informado para transação.");
                }
                ContaEntity conta =  contaRepository.findById(request.getContaId())
                        .orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada."));
                entity.setConta(conta);
            }
            case CARTEIRA ->{
            }
//            case PIX ->{
//            }


        }

        TransacaoEntity save = transacaoRepository.save(entity);
        return transacaoMapper.toResponse(save);
    }


}
