package com.perro.dashboard.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perro.dashboard.client.PetServiceClient;
import com.perro.dashboard.dto.EstadisticasDashboard;
import com.perro.dashboard.model.Perro;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DashboardService {
    
    private final PetServiceClient petServiceClient;
    
    public Mono<EstadisticasDashboard> obtenerEstadisticas() {
        return Mono.fromCallable(() -> {
            List<Perro> perros = petServiceClient.listarPerros("Bearer fake-jwt-token-1");
            
            EstadisticasDashboard estadisticas = new EstadisticasDashboard();
            estadisticas.setTotalPerros((long) perros.size());
            
            // Estadísticas por tamaño
            long perrosPorTamano = perros.stream()
                    .filter(p -> p.getTamano() != null)
                    .count();
            estadisticas.setPerrosPorTamano(perrosPorTamano);
            
            // Estadísticas por raza
            long perrosPorRaza = perros.stream()
                    .filter(p -> p.getRaza() != null)
                    .count();
            estadisticas.setPerrosPorRaza(perrosPorRaza);
            
            // Estadísticas por ubicación
            long perrosPorUbicacion = perros.stream()
                    .filter(p -> p.getUbicacion() != null)
                    .count();
            estadisticas.setPerrosPorUbicacion(perrosPorUbicacion);
            
            return estadisticas;
        });
    }
    
    public Mono<List<Perro>> obtenerPerrosPorDuenio(String duenio) {
        return Mono.fromCallable(() -> {
            List<Perro> perros = petServiceClient.listarPerros("Bearer fake-jwt-token-1");
            return perros.stream()
                    .filter(p -> duenio.equals(p.getDuenio()))
                    .collect(Collectors.toList());
        });
    }
}