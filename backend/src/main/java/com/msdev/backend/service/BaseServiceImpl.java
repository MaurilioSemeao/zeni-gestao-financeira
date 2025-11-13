package com.msdev.backend.service;

import com.msdev.backend.exception.RecursoNaoEncontradoException;
import com.msdev.backend.utils.BaseMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseServiceImpl<T, ID, Req, Res> {

    protected final JpaRepository<T, ID> repository;
    protected final BaseMapper<T, Req, Res> mapper;
    protected String message;

    public BaseServiceImpl(
            JpaRepository<T, ID> repository,
            BaseMapper<T, Req, Res> mapper,
            String message){
        this.repository = repository;
        this.mapper = mapper;
        this.message = message;
    }

    @Transactional(readOnly = true)
    public List<Res> findAll(){
       List<T> entities = repository.findAll();
       return mapper.toResponseList(entities);
    }

    @Transactional(readOnly = true)
    public Res findById(ID id){
        T entity = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(this.message + " não encontrado."));
        return mapper.toResponse(entity);
    }

    @Transactional
    public Res create(Req request){
        T entity = mapper.toEntity(request);
        beforeCreate(entity, request);
        T save = repository.save(entity);
        beforeCreate(save, request);
        return mapper.toResponse(save);
    }

    @Transactional
    public Res update(ID id, Req request){
        T entity = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(this.message + " não encontrado para update."));
        mapper.updateEntity(request, entity);
        T save = repository.save(entity);
        return mapper.toResponse(save);
    }

    @Transactional
    public void delete(ID id){
       T entity = repository.findById(id)
               .orElseThrow(() -> new RecursoNaoEncontradoException(this.message + " não encontrado deletar."));

       repository.delete(entity);
    }

    public void beforeCreate(T entity, Req request ){}

    public void afterCreate(T entity, Req request){}


}
