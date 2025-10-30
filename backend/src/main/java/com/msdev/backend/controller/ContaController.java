package com.msdev.backend.controller;


import com.msdev.backend.dto.request.ContaRequest;
import com.msdev.backend.dto.response.ContaResponse;
import com.msdev.backend.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/contas")
public class ContaController {
    
    private final ContaService contaService;
    
    public ContaController(ContaService contaService){
        this.contaService = contaService;
    }

    @GetMapping
    public ResponseEntity<List<ContaResponse>> findAll(){
        List<ContaResponse> contas = contaService.findAll();
        return ResponseEntity.ok().body(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponse> findById(@PathVariable Long id){
        ContaResponse conta = contaService.findById(id);
        return ResponseEntity.ok().body(conta);
    }

    @PostMapping
    public ResponseEntity<ContaResponse> createAccount(@RequestBody ContaRequest request){
        ContaResponse conta = contaService.createAccount(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(conta)
                .toUri();

        return ResponseEntity.created(uri).body(conta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaResponse> update(@PathVariable Long id, @RequestBody ContaRequest contaAtualizada){
        ContaResponse atualizada = contaService.update(id, contaAtualizada);
        return  ResponseEntity.ok().body(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
