package com.msdev.backend.controller;


import com.msdev.backend.dto.UsuarioDTO;
import com.msdev.backend.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        List<UsuarioDTO> lista = usuarioService.findALl();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
        UsuarioDTO usuario = usuarioService.findById(id);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping()
    public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioDTO usuario){
        UsuarioDTO novoUsuario = usuarioService.inset(usuario);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoUsuario.getId())
                .toUri();
        return ResponseEntity.created(uri).body(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO atualizaUsuario){
       UsuarioDTO usuarioAtualizado =  usuarioService.update(id, atualizaUsuario);
       return ResponseEntity.ok().body(usuarioAtualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
