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
public class ContaService {

    private final ContaRepository contaRepository;
    private final ContaMapper contaMapper;

    public ContaService(ContaRepository contaRepository, ContaMapper contaMapper){
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
    }

    public ContaResponse findById(Long id){
        ContaEntity entity = contaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada."));

        return contaMapper.toResponse(entity);
    }

    public List<ContaResponse> findAll(){
        List<ContaEntity> entities = contaRepository.findAll();

        return  entities.stream()
                .map(contaMapper::toResponse)
                .toList();
    }

    public ContaResponse createAccount(ContaRequest request){
        ContaEntity entity = contaMapper.toEntity(request);
        ContaEntity save = contaRepository.save(entity);
        return contaMapper.toResponse(save);
    }

    public ContaResponse update(Long id, ContaRequest contaAtualizada){
        ContaEntity conta = contaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cartão não encontrado para atualizar."));
        contaMapper.atualizaConta(contaAtualizada, conta);
        contaRepository.save(conta);
        return contaMapper.toResponse(conta);
    }

    public void delete(Long id){
        ContaEntity conta = contaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cartão não encontrado para deletar."));

        contaRepository.delete(conta);
    }

}
