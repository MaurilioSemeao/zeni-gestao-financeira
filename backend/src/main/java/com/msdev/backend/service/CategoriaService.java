package com.msdev.backend.service;

import com.msdev.backend.dto.request.CategoriaRequest;
import com.msdev.backend.dto.response.CategoriaResponse;
import com.msdev.backend.entity.CategoriaEntity;
import com.msdev.backend.repository.CategoriaRepository;

import com.msdev.backend.utils.CategoriaMapper;
import org.springframework.stereotype.Service;


@Service
public class CategoriaService extends BaseServiceImpl<CategoriaEntity, Long, CategoriaRequest, CategoriaResponse> {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        super(categoriaRepository, categoriaMapper, "Categoria");

        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }
}
