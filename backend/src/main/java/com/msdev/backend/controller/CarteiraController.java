package com.msdev.backend.controller;

import com.msdev.backend.dto.request.CarteiraRequest;
import com.msdev.backend.dto.response.CarteiraResponse;
import com.msdev.backend.service.CarteiraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/carteiras")
public class CarteiraController {

    private final CarteiraService carteiraService;

    public CarteiraController(CarteiraService carteiraService){
        this.carteiraService = carteiraService;
    }

    @GetMapping
    public ResponseEntity<List<CarteiraResponse>> findAll(){
        List<CarteiraResponse> carteiras = carteiraService.findAll();
        return ResponseEntity.ok().body(carteiras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarteiraResponse> findById(@PathVariable Long id){
        CarteiraResponse carteira = carteiraService.findById(id);
        return ResponseEntity.ok().body(carteira);
    }

    @PostMapping
    public ResponseEntity<CarteiraResponse> create(@Valid @RequestBody CarteiraRequest request){
        CarteiraResponse carteira = carteiraService.create(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(carteira)
                .toUri();
        return ResponseEntity.created(uri).body(carteira);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarteiraResponse> update(@PathVariable Long id, @RequestBody CarteiraRequest request){
        CarteiraResponse carteira = carteiraService.update(id, request);
        return ResponseEntity.ok().body(carteira);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        carteiraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
