package com.msdev.backend.controller;


import com.msdev.backend.dto.request.AuthenticationRequest;
import com.msdev.backend.dto.request.UsuarioRequest;
import com.msdev.backend.dto.response.AccessResponse;
import com.msdev.backend.dto.response.UsuarioResponse;
import com.msdev.backend.security.service.AuthenticationService;
import com.msdev.backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationService authenticationService, UsuarioService usuarioService) {
        this.authenticationService = authenticationService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<AccessResponse> login(@RequestBody AuthenticationRequest userAuth){
        return ResponseEntity.ok().body(authenticationService.login(userAuth));
    }

    @PostMapping("/newuser")
    public ResponseEntity<UsuarioResponse> insetNewUser(@Valid @RequestBody UsuarioRequest request){

        UsuarioResponse newUser = usuarioService.create(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser)
                .toUri();
        return ResponseEntity.created(uri).body(newUser);
    }

}
