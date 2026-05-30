package com.msdev.backend.service;

import com.msdev.backend.dto.request.ExtratoMensalRequest;
import com.msdev.backend.dto.response.ExtratoMensalResponse;
import com.msdev.backend.entity.ExtratoMensalEntity;
import com.msdev.backend.entity.TransacaoEntity;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.repository.ExtratoMensalRepository;
import com.msdev.backend.security.service.AuthenticationService;
import com.msdev.backend.utils.ExtratoMensalMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;

@Service
public class ExtratoMensalService extends BaseServiceImpl<ExtratoMensalEntity, Long, ExtratoMensalRequest, ExtratoMensalResponse>{

    private final ExtratoMensalRepository extratoMensalRepository;
    private final ExtratoMensalMapper extratoMensalMapper;
    private final AuthenticationService authenticationService;

    public ExtratoMensalService(ExtratoMensalRepository extratoMensalRepository, ExtratoMensalMapper extratoMensalMapper, AuthenticationService authenticationService){
        super(extratoMensalRepository, extratoMensalMapper, "ExtratoMensal");
        this.extratoMensalRepository = extratoMensalRepository;
        this.extratoMensalMapper = extratoMensalMapper;
        this.authenticationService = authenticationService;
    }

    @Override
    protected List<ExtratoMensalEntity> fetchAllEntities(){
        UsuarioEntity usuario = authenticationService.getLoggedIUser();
        return  extratoMensalRepository.findAllByUsuarioIdOrderByMesReferenciaDesc(usuario.getId());
    }
}
