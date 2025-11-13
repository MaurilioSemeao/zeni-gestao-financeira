package com.msdev.backend.controller;

import com.msdev.backend.dto.request.ExtratoMensalRequest;
import com.msdev.backend.dto.response.ExtratoMensalResponse;
import com.msdev.backend.service.ExtratoMensalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/extratomensal")
public class ExtratoMensalController {

    private final ExtratoMensalService extratoMensalService;


    public ExtratoMensalController(ExtratoMensalService extratoMensalService) {
        this.extratoMensalService = extratoMensalService;
    }

    @GetMapping
    public ResponseEntity<List<ExtratoMensalResponse>> getAll(){
        List<ExtratoMensalResponse> extratos = extratoMensalService.findAll();
        return ResponseEntity.ok().body(extratos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtratoMensalResponse> getById(@PathVariable Long id){
        ExtratoMensalResponse extrato = extratoMensalService.findById(id);
        return ResponseEntity.ok().body(extrato);
    }

    @PostMapping
    public ResponseEntity<ExtratoMensalResponse> create(@Valid @RequestBody ExtratoMensalRequest request){
        ExtratoMensalResponse extrato = extratoMensalService.create(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(extrato)
                .toUri();

        return ResponseEntity.created(uri).body(extrato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExtratoMensalResponse> update(@PathVariable Long id, @RequestBody ExtratoMensalRequest request){
        ExtratoMensalResponse atualizado = extratoMensalService.update(id, request);
        return ResponseEntity.ok().body(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        extratoMensalService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
