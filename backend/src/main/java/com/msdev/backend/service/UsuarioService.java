package com.msdev.backend.service;


import com.msdev.backend.dto.UsuarioDTO;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> findALl(){
       List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return  usuarios.stream().map(UsuarioDTO:: new).toList();
    }

    public UsuarioDTO findById(Long id){
        UsuarioEntity usuario = usuarioRepository.getReferenceById(id);
        return new UsuarioDTO(usuario);
    }

    public UsuarioDTO inset(UsuarioDTO usuario){
        UsuarioEntity novoUsuario =  new UsuarioEntity(usuario);
        return new UsuarioDTO(usuarioRepository.save(novoUsuario));
    }

    public UsuarioDTO update(Long id, UsuarioDTO usuarioAtualizado){
        UsuarioEntity usuario = usuarioRepository.getReferenceById(id);
        UsuarioEntity atualizado = new UsuarioEntity(usuarioAtualizado);

        atualizaDados(usuario, atualizado);

        return new UsuarioDTO(usuario);
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
