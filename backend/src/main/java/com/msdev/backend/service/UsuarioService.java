package com.msdev.backend.service;


import com.msdev.backend.dto.request.UsuarioRequest;
import com.msdev.backend.dto.response.UsuarioResponse;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.exception.RecursoNaoEncontradoException;
import com.msdev.backend.repository.UsuarioRepository;
import com.msdev.backend.utils.UsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService extends BaseServiceImpl<UsuarioEntity, Long, UsuarioRequest, UsuarioResponse> {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper){
        super(usuarioRepository,usuarioMapper,"Usuário");
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }




}
