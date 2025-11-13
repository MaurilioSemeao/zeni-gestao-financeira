package com.msdev.backend.service;

import com.msdev.backend.dto.request.ExtratoMensalRequest;
import com.msdev.backend.dto.response.ExtratoMensalResponse;
import com.msdev.backend.entity.ExtratoMensalEntity;
import com.msdev.backend.repository.ExtratoMensalRepository;
import com.msdev.backend.utils.ExtratoMensalMapper;
import org.springframework.stereotype.Service;

import java.io.Serial;

@Service
public class ExtratoMensalService extends BaseServiceImpl<ExtratoMensalEntity, Long, ExtratoMensalRequest, ExtratoMensalResponse>{

    private final ExtratoMensalRepository extratoMensalRepository;
    private final ExtratoMensalMapper extratoMensalMapper;

    public ExtratoMensalService(ExtratoMensalRepository extratoMensalRepository, ExtratoMensalMapper extratoMensalMapper){
        super(extratoMensalRepository, extratoMensalMapper, "ExtratoMensal");
        this.extratoMensalRepository = extratoMensalRepository;
        this.extratoMensalMapper = extratoMensalMapper;
    }
}
