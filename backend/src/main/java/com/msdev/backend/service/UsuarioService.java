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
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper){
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioResponse> findALl(){
       List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return  usuarios.stream().map(usuarioMapper::toResponse).toList();
    }

    public UsuarioResponse findById(Long id){
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
        return usuarioMapper.toResponse(usuario);
    }

    public UsuarioResponse inset(UsuarioRequest usuario){
        UsuarioEntity novoUsuario =  usuarioMapper.toEntity(usuario);

        return usuarioMapper.toResponse(usuarioRepository.save(novoUsuario));
    }

    public UsuarioResponse update(Long id, UsuarioRequest usuarioAtualizado){
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));

        usuarioMapper.atualizaUsuario(usuarioAtualizado, usuario);
        return usuarioMapper.toResponse(usuario);
    }

    public void delete(Long id){
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
        usuarioRepository.delete(usuario);
    }


}
