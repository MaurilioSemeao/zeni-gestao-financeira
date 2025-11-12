package com.msdev.backend.service;

import com.msdev.backend.dto.request.ParcelamentoRequest;
import com.msdev.backend.dto.response.ParcelamentoResponse;
import com.msdev.backend.entity.ParcelamentoEntity;
import com.msdev.backend.repository.ParcelamentoRepository;
import com.msdev.backend.utils.ParcelamentoMapper;
import org.springframework.stereotype.Service;

@Service
public class ParcelamentoService extends BaseServiceImpl<ParcelamentoEntity, Long, ParcelamentoRequest, ParcelamentoResponse> {
    private final ParcelamentoRepository parcelamentoRepository;
    private final ParcelamentoMapper parcelamentoMapper;

    public ParcelamentoService (ParcelamentoRepository parcelamentoRepository, ParcelamentoMapper parcelamentoMapper){
        super(parcelamentoRepository, parcelamentoMapper, "Parcelamento");
        this.parcelamentoMapper = parcelamentoMapper;
        this.parcelamentoRepository = parcelamentoRepository;
    }

}
