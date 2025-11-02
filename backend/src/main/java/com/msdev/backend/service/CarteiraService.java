package com.msdev.backend.service;

import com.msdev.backend.dto.request.CarteiraRequest;
import com.msdev.backend.dto.response.CarteiraResponse;
import com.msdev.backend.entity.CarteiraEntity;
import com.msdev.backend.exception.RecursoNaoEncontradoException;
import com.msdev.backend.repository.CarteiraRepository;
import com.msdev.backend.utils.CarteiraMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListResourceBundle;

@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;
    private final CarteiraMapper carteiraMapper;

    public CarteiraService(CarteiraRepository carteiraRepository, CarteiraMapper carteiraMapper){
        this.carteiraRepository = carteiraRepository;
        this.carteiraMapper = carteiraMapper;
    }

    public CarteiraResponse findById(Long id){
        CarteiraEntity entity = carteiraRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carteira não encontrada"));

        return carteiraMapper.toResponse(entity);
    }

    public List<CarteiraResponse> findAll(){
        List<CarteiraEntity> carteiras = carteiraRepository.findAll();
        return carteiras.stream().map(carteiraMapper::toResponse).toList();
    }

    public CarteiraResponse create(CarteiraRequest request){
        CarteiraEntity entity = carteiraMapper.toEntity(request);
        return carteiraMapper.toResponse(carteiraRepository.save(entity));
    }

    public CarteiraResponse update(Long id, CarteiraRequest request){
        CarteiraEntity entity = carteiraRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carteira não encontrada."));

        carteiraMapper.atualizaCarteira(request, entity);
        return carteiraMapper.toResponse(carteiraRepository.save(entity));

    }

    public void delete(Long id){
        CarteiraEntity entity = carteiraRepository.findById(id)
                        .orElseThrow(() -> new RecursoNaoEncontradoException("Carteira não encontrada."));
        carteiraRepository.delete(entity);
    }

}
