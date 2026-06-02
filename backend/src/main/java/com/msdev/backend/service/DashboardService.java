package com.msdev.backend.service;

import com.msdev.backend.dto.response.ResumoCartaoResponse;
import com.msdev.backend.dto.response.ResumoCategoriaResponse;
import com.msdev.backend.entity.CategoriaEntity;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.repository.CategoriaRepository;
import com.msdev.backend.repository.TransacaoRepository;
import com.msdev.backend.security.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    private final CategoriaRepository categoriaRepository;
    private TransacaoRepository transacaoRepository;
    private AuthenticationService authenticationService;

    public DashboardService(TransacaoRepository transacaoRepository, AuthenticationService authenticationService, CategoriaRepository categoriaRepository){
        this.transacaoRepository = transacaoRepository;
        this.authenticationService = authenticationService;
        this.categoriaRepository = categoriaRepository;
    }

    private LocalDateTime[] calcularIntervalo(String periodo) {
        LocalDate hoje = LocalDate.now();
        LocalDateTime dataInicio;
        LocalDateTime dataFim;

        switch (periodo != null ? periodo.toUpperCase() : "MENSAL") {
            case "SEMANAL":
                dataInicio = hoje.minusDays(7).atStartOfDay();
                dataFim = hoje.atTime(LocalTime.MAX);
                break;
            case "ANUAL":
                dataInicio = hoje.withDayOfYear(1).atStartOfDay();
                dataFim = hoje.withDayOfYear(hoje.lengthOfYear()).atTime(LocalTime.MAX);
                break;
            case "GERAL":
                dataInicio = LocalDateTime.of(2000, 1, 1, 0, 0);
                dataFim = hoje.plusYears(100).atTime(LocalTime.MAX);
                break;
            case "MENSAL":
            default:
                dataInicio = hoje.withDayOfMonth(1).atStartOfDay();
                dataFim = hoje.withDayOfMonth(hoje.lengthOfMonth()).atTime(LocalTime.MAX);
                break;
        }

        return new LocalDateTime[]{dataInicio, dataFim};
    }

    public List<ResumoCategoriaResponse> getGastosPorCategoria(String periodo){
        UsuarioEntity usuario = authenticationService.getLoggedIUser();
        LocalDateTime[] intervalo = calcularIntervalo(periodo);

        List<ResumoCategoriaResponse> lista = transacaoRepository.findGastosPorCategoria(usuario.getId(), intervalo[0], intervalo[1]);

        List<CategoriaEntity> todasCategorias = categoriaRepository.findAllByUsuarioId(usuario.getId());

        Map<String, ResumoCategoriaResponse> mapaGastos = lista.stream()
                .collect(Collectors.toMap(ResumoCategoriaResponse::getNomeCategoria, dto -> dto));

        List<ResumoCategoriaResponse> listaIntegrada = new ArrayList<>();

        for (CategoriaEntity categoria : todasCategorias){
            if(mapaGastos.containsKey(categoria.getNome())){
                listaIntegrada.add(mapaGastos.get(categoria.getNome()));
            }else{
                listaIntegrada.add(new ResumoCategoriaResponse(categoria.getNome(), BigDecimal.ZERO, 0.0));
            }
        }

        BigDecimal totalGeral = listaIntegrada.stream()
                .map(ResumoCategoriaResponse::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if(totalGeral.compareTo(BigDecimal.ZERO) > 0){
            for(ResumoCategoriaResponse item : listaIntegrada){
                BigDecimal porcentagem = item.getValorTotal()
                        .multiply(new BigDecimal(100))
                        .divide(totalGeral, 0, RoundingMode.HALF_UP);

                item.setPorcentagem(porcentagem.doubleValue());
            }
        }

        listaIntegrada.sort((a, b) -> b.getValorTotal().compareTo(a.getValorTotal()));

        return listaIntegrada;
    }

    public List<ResumoCartaoResponse> getGastosPorCartao(String periodo){
        UsuarioEntity usuario = authenticationService.getLoggedIUser();
        LocalDateTime[] intervalo = calcularIntervalo(periodo);

        List<ResumoCartaoResponse> lista = transacaoRepository.findGastosPorCartao(usuario.getId(), intervalo[0], intervalo[1]);

        BigDecimal totalGeral = lista.stream()
                .map(ResumoCartaoResponse::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if(totalGeral.compareTo(BigDecimal.ZERO) > 0){
            for(ResumoCartaoResponse item : lista){
                BigDecimal porcentagem = item.getValorTotal()
                        .multiply(new BigDecimal(100))
                        .divide(totalGeral, 0, RoundingMode.HALF_UP);

                item.setPorcentagem(porcentagem.doubleValue());
            }
        }

        lista.sort((a, b) -> b.getValorTotal().compareTo(a.getValorTotal()));

        return lista;
    }
}

