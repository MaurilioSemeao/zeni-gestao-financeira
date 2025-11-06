package com.msdev.backend.service;

import com.msdev.backend.dto.request.ContaRequest;
import com.msdev.backend.dto.response.ContaResponse;
import com.msdev.backend.entity.ContaEntity;
import com.msdev.backend.exception.RecursoNaoEncontradoException;
import com.msdev.backend.repository.ContaRepository;
import com.msdev.backend.utils.ContaMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService extends BaseServiceImpl<ContaEntity, Long, ContaRequest, ContaResponse>{

    private final ContaRepository contaRepository;
    private final ContaMapper contaMapper;

    public ContaService(ContaRepository contaRepository, ContaMapper contaMapper){
        super(contaRepository,contaMapper, "Conta");
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
    }



}
