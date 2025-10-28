package com.msdev.backend.service;

import com.msdev.backend.dto.request.TransacaoRequest;
import com.msdev.backend.dto.response.TransacaoResponse;
import com.msdev.backend.entity.CartaoEntity;
import com.msdev.backend.entity.TransacaoEntity;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.exception.RecursoNaoEncontradoException;
import com.msdev.backend.exception.transacao.TransacaoInvalidaException;
import com.msdev.backend.repository.CartaoRepository;
import com.msdev.backend.repository.TransacaoRepository;
import com.msdev.backend.repository.UsuarioRepository;
import com.msdev.backend.utils.TransacaoMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final CartaoRepository cartaoRepository;
    private final UsuarioRepository usuarioRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, CartaoRepository cartaoRepository, UsuarioRepository usuarioRepository){
        this.transacaoRepository = transacaoRepository;
        this.cartaoRepository = cartaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public TransacaoResponse create(TransacaoRequest request){
        TransacaoEntity entity = TransacaoMapper.toEntity(request);

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

//            case CONTA ->{
//            }
//            case CARTEIRA ->{
//            }
//            case PIX ->{
//            }


        }

        TransacaoEntity save = transacaoRepository.save(entity);
        return TransacaoMapper.toResponse(save);
    }


    public List<TransacaoResponse> findAll(){
        List<TransacaoEntity> entities = transacaoRepository.findAll();

        return entities.stream()
                .map(TransacaoMapper::toResponse)
                .toList();
    }

    public TransacaoResponse findById(Long id){
        TransacaoEntity entity = transacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Transação não encontrada."));

        return TransacaoMapper.toResponse(entity);
    }

    public TransacaoResponse update(Long id, TransacaoRequest request){
        TransacaoEntity entity = transacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Transação não encontrada."));

        TransacaoEntity dadosAtualizado = TransacaoMapper.toEntity(request);

        atualizaDados(entity, dadosAtualizado);

        TransacaoEntity save = transacaoRepository.save(entity);
        return TransacaoMapper.toResponse(save);
    }


    public void delete(Long id){
        TransacaoEntity entity = transacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Transação não encontrada pra deletar"));
        transacaoRepository.delete(entity);
    }



    private void atualizaDados(TransacaoEntity entity, TransacaoEntity atualizado){
        if(atualizado.getDescricao() != null) entity.setDescricao(atualizado.getDescricao());
        if(atualizado.getMeioPagamento() != null) entity.setMeioPagamento(atualizado.getMeioPagamento());
        if(atualizado.getDataTransacao() != null) entity.setDataTransacao(atualizado.getDataTransacao());
        if(atualizado.getCartao() != null) entity.setCartao(atualizado.getCartao());
        if(atualizado.getTipo() != null) entity.setTipo(atualizado.getTipo());
        if(atualizado.getValor() != null) entity.setValor(atualizado.getValor());

    }


}
