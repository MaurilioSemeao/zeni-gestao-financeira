package com.msdev.backend.controller;


import com.msdev.backend.dto.request.TransacaoRequest;
import com.msdev.backend.dto.response.TransacaoResponse;
import com.msdev.backend.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TransferQueue;

@RestController
@CrossOrigin
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;


    public TransacaoController(TransacaoService transacaoService    ){
        this.transacaoService = transacaoService;

    }

    @GetMapping
    public ResponseEntity<List<TransacaoResponse>> findAll(){
        List<TransacaoResponse> transacao = transacaoService.findAll();
        return ResponseEntity.ok().body(transacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponse> findById(@PathVariable Long id){
        TransacaoResponse transacao = transacaoService.findById(id);
        return ResponseEntity.ok().body(transacao);
    }

    @GetMapping("/extratomensal/{id}")
    public ResponseEntity<List<TransacaoResponse>> fildAllById(@PathVariable Long id){
        List<TransacaoResponse> transacoes = transacaoService.findByExtratoMensalByUsuarioId(id);
        return ResponseEntity.ok().body(transacoes);
    }

    @PostMapping
    public ResponseEntity<TransacaoResponse> create(@Valid @RequestBody TransacaoRequest request){
        TransacaoResponse transacao = transacaoService.create(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transacao)
                .toUri();

        return ResponseEntity.created(uri).body(transacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoResponse> update(@PathVariable Long id, @RequestBody TransacaoRequest atualizacao){
        TransacaoResponse update = transacaoService.update(id ,atualizacao);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        transacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
