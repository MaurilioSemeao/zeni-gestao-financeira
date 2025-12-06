package com.msdev.backend.service;

import com.msdev.backend.dto.request.ContaRequest;
import com.msdev.backend.dto.response.ContaResponse;
import com.msdev.backend.entity.CategoriaEntity;
import com.msdev.backend.entity.ContaEntity;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.exception.RecursoNaoEncontradoException;
import com.msdev.backend.repository.ContaRepository;
import com.msdev.backend.security.service.AuthenticationService;
import com.msdev.backend.utils.ContaMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService extends BaseServiceImpl<ContaEntity, Long, ContaRequest, ContaResponse>{

    private final ContaRepository contaRepository;
    private final ContaMapper contaMapper;
    private final AuthenticationService authenticationService;

    public ContaService(ContaRepository contaRepository, ContaMapper contaMapper, AuthenticationService authenticationService){
        super(contaRepository,contaMapper, "Conta");
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
        this.authenticationService = authenticationService;
    }

    @Override
    protected List<ContaEntity> fetchAllEntities(){
        UsuarioEntity usuarioLogado = authenticationService.getLoggedIUser();
        return  contaRepository.findAllByUsuarioId(usuarioLogado.getId());
    }

}
