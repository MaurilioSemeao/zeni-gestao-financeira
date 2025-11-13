package com.msdev.backend.security.service;


import com.msdev.backend.dto.request.AuthenticationRequest;
import com.msdev.backend.dto.response.AccessResponse;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.repository.UsuarioRepository;
import com.msdev.backend.security.jwt.JwtUtils;
import com.msdev.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UsuarioRepository usuarioRepository, UsuarioService usuarioService){
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    public AccessResponse login(AuthenticationRequest authRequest){

        UsernamePasswordAuthenticationToken userAuthentication =
                new UsernamePasswordAuthenticationToken(
                        authRequest.getLogin(),
                        authRequest.getPassword()
                );

        Authentication authentication = authenticationManager.authenticate(userAuthentication);

        UserDetailsImpl userPrincipalAuthentication =(UserDetailsImpl) authentication.getPrincipal();

        String token = jwtUtils.generateJwtToken(userPrincipalAuthentication);


        return new AccessResponse(
                token,
                userPrincipalAuthentication.getId(),
                userPrincipalAuthentication.getEmail(),
                userPrincipalAuthentication.getUsername()
        );

    }


    public UsuarioEntity getLoggedIUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken){
            throw new RuntimeException("Usuário não autenticado. Não é possível realiza operações ");
        }

        Object principal = authentication.getPrincipal();

        String username;
        if(principal instanceof UserDetails){
            username = ((UserDetails) principal).getUsername();
        }else{
            username = principal.toString();
        }

        UsuarioEntity usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuário " + username + " não Está Logado"));

        return usuario;

    }

}
