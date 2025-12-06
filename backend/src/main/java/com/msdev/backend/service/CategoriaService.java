package com.msdev.backend.service;

import com.msdev.backend.dto.request.CategoriaRequest;
import com.msdev.backend.dto.response.CategoriaResponse;
import com.msdev.backend.entity.CategoriaEntity;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.repository.CategoriaRepository;

import com.msdev.backend.security.service.AuthenticationService;
import com.msdev.backend.utils.CategoriaMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoriaService extends BaseServiceImpl<CategoriaEntity, Long, CategoriaRequest, CategoriaResponse> {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;
    private final AuthenticationService authenticationService;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper,AuthenticationService authenticationService) {
        super(categoriaRepository, categoriaMapper, "Categoria");

        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
        this.authenticationService = authenticationService;
    }

    @Override
    protected List<CategoriaEntity> fetchAllEntities(){
        UsuarioEntity usuarioLogado = authenticationService.getLoggedIUser();
        return  categoriaRepository.findAllByUsuarioId(usuarioLogado.getId());
    }

}
