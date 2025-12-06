package com.msdev.backend.service;

import com.msdev.backend.dto.request.ParcelamentoRequest;
import com.msdev.backend.dto.response.ParcelamentoResponse;
import com.msdev.backend.entity.ExtratoMensalEntity;
import com.msdev.backend.entity.ParcelamentoEntity;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.repository.ParcelamentoRepository;
import com.msdev.backend.security.service.AuthenticationService;
import com.msdev.backend.utils.ParcelamentoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelamentoService extends BaseServiceImpl<ParcelamentoEntity, Long, ParcelamentoRequest, ParcelamentoResponse> {
    private final ParcelamentoRepository parcelamentoRepository;
    private final ParcelamentoMapper parcelamentoMapper;
    private final AuthenticationService authenticationService;

    public ParcelamentoService (ParcelamentoRepository parcelamentoRepository, ParcelamentoMapper parcelamentoMapper, AuthenticationService authenticationService){
        super(parcelamentoRepository, parcelamentoMapper, "Parcelamento");
        this.parcelamentoMapper = parcelamentoMapper;
        this.parcelamentoRepository = parcelamentoRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    protected List<ParcelamentoEntity> fetchAllEntities(){
        UsuarioEntity usuario = authenticationService.getLoggedIUser();
        return  parcelamentoRepository.findAllByUsuarioId(usuario.getId());
    }

}
