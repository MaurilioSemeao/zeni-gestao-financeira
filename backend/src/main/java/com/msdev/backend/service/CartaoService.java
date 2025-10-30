package com.msdev.backend.service;

import com.msdev.backend.dto.request.CartaoRequest;
import com.msdev.backend.dto.response.CartaoResponse;
import com.msdev.backend.entity.CartaoEntity;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.exception.RecursoNaoEncontradoException;
import com.msdev.backend.repository.CartaoRepository;
import com.msdev.backend.utils.CartaoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final CartaoMapper cartaoMapper;

    public CartaoService(CartaoRepository cartaoRepository, CartaoMapper cartaoMapper){
        this.cartaoMapper = cartaoMapper;
        this.cartaoRepository = cartaoRepository;
    }

    public List<CartaoResponse> findAll(){
        List<CartaoEntity> cartoes = cartaoRepository.findAll();
        return cartoes.stream()
                .map(cartaoMapper::toResponse)
                .toList();

    }

    public CartaoResponse findById(Long id){
        CartaoEntity cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cartão não encontrado."));
        return cartaoMapper.toResponse(cartao);
    }

    public CartaoResponse insert(CartaoRequest request){
        CartaoEntity cartao = cartaoMapper.toEntity(request);
        CartaoEntity save = cartaoRepository.save(cartao);

        return cartaoMapper.toResponse(save);
    }

    public CartaoResponse update(Long id, CartaoRequest request){
        CartaoEntity cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cartão não encontrado."));

        cartaoMapper.atualizaCartao(request, cartao);
        CartaoEntity save = cartaoRepository.save(cartao);
        return cartaoMapper.toResponse(save);

    }

    public void delete (Long id){
        CartaoEntity cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cartão não encontrado."));
        cartaoRepository.delete(cartao);
    }




}
