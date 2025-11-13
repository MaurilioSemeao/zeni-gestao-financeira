package com.msdev.backend.service;


import com.msdev.backend.dto.request.UsuarioRequest;
import com.msdev.backend.dto.response.UsuarioResponse;
import com.msdev.backend.entity.CarteiraEntity;
import com.msdev.backend.entity.CategoriaEntity;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.exception.RecursoNaoEncontradoException;
import com.msdev.backend.repository.UsuarioRepository;
import com.msdev.backend.utils.UsuarioMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService extends BaseServiceImpl<UsuarioEntity, Long, UsuarioRequest, UsuarioResponse> {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper){
        super(usuarioRepository,usuarioMapper,"Usuário");
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public void beforeCreate(UsuarioEntity entity ,UsuarioRequest request){
        UsuarioEntity usuario = usuarioMapper.toEntity(request);

        CarteiraEntity carteira = new CarteiraEntity();
        carteira.setUsuario(usuario);
        usuario.setCarteira(carteira);

        Set<String> categoriasPadrao = Set.of(
                "Alimentação",
                "Transporte",
                "Moradia",
                "Saúde",
                "Lazer",
                "Educação",
                "Salário",
                "Outros"
        );

        Set<CategoriaEntity> categorias = categoriasPadrao.stream()
                .map(nome -> new CategoriaEntity(nome, true ,usuario))
                .collect(Collectors.toSet());

        usuario.setCategorias(categorias);
    }


}
