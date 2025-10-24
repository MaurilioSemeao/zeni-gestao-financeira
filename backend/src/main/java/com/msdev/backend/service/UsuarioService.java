package com.msdev.backend.service;


import com.msdev.backend.dto.request.UsuarioRequest;
import com.msdev.backend.dto.response.UsuarioResponse;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.repository.UsuarioRepository;
import com.msdev.backend.utils.UsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponse> findALl(){
       List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return  usuarios.stream().map(UsuarioMapper::toResponse).toList();
    }

    public UsuarioResponse findById(Long id){
        UsuarioEntity usuario = usuarioRepository.getReferenceById(id);
        return UsuarioMapper.toResponse(usuario);
    }

    public UsuarioResponse inset(UsuarioRequest usuario){
        UsuarioEntity novoUsuario =  UsuarioMapper.toEntity(usuario);

        return UsuarioMapper.toResponse(usuarioRepository.save(novoUsuario));
    }

    public UsuarioResponse update(Long id, UsuarioRequest usuarioAtualizado){
        UsuarioEntity usuario = usuarioRepository.getReferenceById(id);
        UsuarioEntity atualizado = UsuarioMapper.toEntity(usuarioAtualizado);

        atualizaDados(usuario, atualizado);

        return UsuarioMapper.toResponse(usuario);
    }

    public void delete(Long id){
        UsuarioEntity usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
    }


    private void atualizaDados(UsuarioEntity usuario, UsuarioEntity usuarioAtualizado){
        if(usuarioAtualizado.getNome() != null) usuario.setNome(usuarioAtualizado.getNome());
        if(usuarioAtualizado.getEmail() != null) usuario.setEmail(usuarioAtualizado.getEmail());
        if(usuarioAtualizado.getSenha() != null) usuario.setSenha(usuarioAtualizado.getSenha());
        if(usuarioAtualizado.getTipoUsuario() != null) usuario.setTipoUsuario(usuarioAtualizado.getTipoUsuario());
    }


}
