package com.msdev.backend.service;

import com.msdev.backend.dto.request.CartaoRequest;
import com.msdev.backend.dto.response.CartaoResponse;
import com.msdev.backend.entity.CartaoEntity;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.repository.CartaoRepository;
import com.msdev.backend.utils.CartaoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    public CartaoService(CartaoRepository cartaoRepository){
        this.cartaoRepository = cartaoRepository;
    }

    public List<CartaoResponse> findAll(){
        List<CartaoEntity> cartoes = cartaoRepository.findAll();
        return cartoes.stream()
                .map(CartaoMapper::toResponse)
                .toList();

    }

    public CartaoResponse findById(Long id){
        CartaoEntity cartao = cartaoRepository.getReferenceById(id);
        return CartaoMapper.toResponse(cartao);
    }

    public CartaoResponse insert(CartaoRequest request){
        CartaoEntity cartao = CartaoMapper.toEntity(request);
        CartaoEntity save = cartaoRepository.save(cartao);

        return CartaoMapper.toResponse(save);
    }

    public CartaoResponse update(Long id, CartaoRequest request){
        CartaoEntity cartao = cartaoRepository.getReferenceById(id);

        CartaoEntity atualizado = CartaoMapper.toEntity(request);

        atualizaDados(cartao, atualizado);

        CartaoEntity save = cartaoRepository.save(cartao);

        return CartaoMapper.toResponse(save);

    }

    public void delete (Long id){
        CartaoEntity cartao = cartaoRepository.getReferenceById(id);
        cartaoRepository.delete(cartao);
    }


    private void atualizaDados(CartaoEntity cartao, CartaoEntity cartaoAtualizado){
        if(cartaoAtualizado.getApelido() != null) cartao.setApelido(cartaoAtualizado.getApelido());
        if(cartaoAtualizado.getUltimosDigitos() != null) cartao.setUltimosDigitos(cartaoAtualizado.getUltimosDigitos());
    }

}
