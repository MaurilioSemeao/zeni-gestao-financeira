package com.msdev.backend.service;


import com.msdev.backend.dto.request.UsuarioRequest;
import com.msdev.backend.dto.response.UsuarioResponse;
import com.msdev.backend.entity.CarteiraEntity;
import com.msdev.backend.entity.CategoriaEntity;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.exception.RecursoNaoEncontradoException;
import com.msdev.backend.repository.UsuarioRepository;
import com.msdev.backend.utils.UsuarioMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService extends BaseServiceImpl<UsuarioEntity, Long, UsuarioRequest, UsuarioResponse> {

    private PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder){
        super(usuarioRepository,usuarioMapper,"Usuário");
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void beforeCreate(UsuarioEntity entity ,UsuarioRequest request){

       entity.setSenha(passwordEncoder.encode(entity.getSenha()));


    }

    @Override
    public void afterCreate(UsuarioEntity save, UsuarioRequest request){
        CarteiraEntity carteira = new CarteiraEntity();
        carteira.setUsuario(save);
        save.setCarteira(carteira);

        Set<String> categoriasPadrao = Set.of(
                "Alimentação",
                "Transporte",
                "Moradia",
                "Saúde",
                "Lazer",
                "Educação",
                "Outros"
        );

        Set<CategoriaEntity> categorias = categoriasPadrao.stream()
                .map(nome -> new CategoriaEntity(nome, true ,save))
                .collect(Collectors.toSet());

        save.setCategorias(categorias);
    }

    public UsuarioEntity getUsuarioLogado(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            throw new RecursoNaoEncontradoException("Nenhum usuário autenticado encontrado.");
        }

        String email = authentication.getName();

        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
    }
}
