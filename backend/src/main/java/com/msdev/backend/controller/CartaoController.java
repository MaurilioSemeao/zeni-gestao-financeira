package com.msdev.backend.controller;


import com.msdev.backend.dto.request.CartaoRequest;
import com.msdev.backend.dto.response.CartaoResponse;
import com.msdev.backend.service.CartaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cartoes")
public class CartaoController {

    private final CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService){
        this.cartaoService = cartaoService;
    }

    @GetMapping
    public ResponseEntity<List<CartaoResponse>> findAll(){
        List<CartaoResponse> cartoes = cartaoService.findAll();
        return ResponseEntity.ok().body(cartoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoResponse> findById(@PathVariable Long id){
        CartaoResponse catao = cartaoService.findById(id);

        return ResponseEntity.ok().body(catao);
    }

    @PostMapping
    public ResponseEntity<CartaoResponse> create(@Valid @RequestBody CartaoRequest request){
        CartaoResponse cartao = cartaoService.create(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cartao)
                .toUri();

        return ResponseEntity.created(uri).body(cartao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartaoResponse> update(@PathVariable Long id, @RequestBody CartaoRequest atualizado){
        CartaoResponse cartao = cartaoService.update(id, atualizado);
        return ResponseEntity.ok().body(cartao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cartaoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
