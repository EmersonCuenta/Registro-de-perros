package com.perro.dashboard.dto;

import lombok.Data;

@Data
public class EstadisticasDashboard {
    private Long totalPerros;
    private Long perrosPorTamano;
    private Long perrosPorRaza;
    private Long perrosPorUbicacion;
}