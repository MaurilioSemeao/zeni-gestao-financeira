package com.msdev.backend.service;

import com.msdev.backend.dto.request.CartaoRequest;
import com.msdev.backend.dto.response.CartaoResponse;
import com.msdev.backend.entity.CartaoEntity;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.repository.CartaoRepository;
import com.msdev.backend.security.service.AuthenticationService;
import com.msdev.backend.utils.CartaoMapper;
import org.springframework.stereotype.Service;

@Service
public class CartaoService extends BaseServiceImpl<CartaoEntity, Long, CartaoRequest, CartaoResponse> {

    private final CartaoRepository cartaoRepository;
    private final CartaoMapper cartaoMapper;
    private final AuthenticationService authenticationService;

    public CartaoService(CartaoRepository cartaoRepository, CartaoMapper cartaoMapper, AuthenticationService authenticationService){
        super(cartaoRepository,cartaoMapper, "Cartão");
        this.cartaoMapper = cartaoMapper;
        this.cartaoRepository = cartaoRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public void beforeCreate(CartaoEntity entity, CartaoRequest request){
        UsuarioEntity usuario = authenticationService.getLoggedIUser();
        entity.setUsuario(usuario);
    }


}
