package com.perro.dashboard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.perro.dashboard.dto.EstadisticasDashboard;
import com.perro.dashboard.model.Perro;
import com.perro.dashboard.service.DashboardService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/estadisticas")
    public Mono<EstadisticasDashboard> obtenerEstadisticas() {
        return dashboardService.obtenerEstadisticas();
    }

    @GetMapping("/perros-duenio")
    public Mono<List<Perro>> obtenerPerrosPorDuenio(@RequestParam String duenio) {
        return dashboardService.obtenerPerrosPorDuenio(duenio);
    }

    @GetMapping("/perros-duenio/{duenio}")
    public Mono<List<Perro>> obtenerPerrosPorDuenioPath(@PathVariable String duenio) {
        return dashboardService.obtenerPerrosPorDuenio(duenio);
    }
}