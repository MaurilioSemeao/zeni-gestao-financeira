package com.msdev.backend.controller;


import com.msdev.backend.dto.request.UsuarioRequest;
import com.msdev.backend.dto.response.UsuarioResponse;
import com.msdev.backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> findAll(){
        List<UsuarioResponse> lista = usuarioService.findALl();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id){
        UsuarioResponse usuario = usuarioService.findById(id);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping()
    public ResponseEntity<UsuarioResponse> insert(@Valid @RequestBody UsuarioRequest usuario){
        UsuarioResponse novoUsuario = usuarioService.inset(usuario);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoUsuario.getId())
                .toUri();
        return ResponseEntity.created(uri).body(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable Long id, @RequestBody UsuarioRequest atualizaUsuario){
       UsuarioResponse usuarioAtualizado =  usuarioService.update(id, atualizaUsuario);
       return ResponseEntity.ok().body(usuarioAtualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
