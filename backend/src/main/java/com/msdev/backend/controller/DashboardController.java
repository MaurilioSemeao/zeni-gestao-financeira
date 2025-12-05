package com.msdev.backend.controller;

import com.msdev.backend.dto.response.ResumoCategoriaResponse;
import com.msdev.backend.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/dashboard")
public class DashboardController {


    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/resumoCategoria")
    public ResponseEntity<List<ResumoCategoriaResponse>> getResumoCategoria(){
        List<ResumoCategoriaResponse> gastosPorCategoria = dashboardService.getGastosPorCategoria();
        return ResponseEntity.ok().body(gastosPorCategoria);
    }

}
