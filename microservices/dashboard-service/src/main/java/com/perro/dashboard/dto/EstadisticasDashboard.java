package com.perro.dashboard.dto;

import java.util.List;

import lombok.Data;

@Data
public class EstadisticasDashboard {
    private Long totalPerros;
    private List<RazaEstadistica> perrosPorRaza;
    private List<EdadEstadistica> perrosPorEdad;
    private List<UbicacionEstadistica> perrosPorUbicacion;
    private List<Perro> perrosPorDuenio;
    
    @Data
    public static class RazaEstadistica {
        private String raza;
        private Long cantidad;
        private Double porcentaje;
    }
    
    @Data
    public static class EdadEstadistica {
        private String categoria;
        private Long cantidad;
        private String rango;
    }
    
    @Data
    public static class UbicacionEstadistica {
        private String ubicacion;
        private Long cantidad;
        private Double latitud;
        private Double longitud;
    }
    
    @Data
    public static class Perro {
        private Long id;
        private String nombre;
        private String raza;
        private String tamano;
        private String ubicacion;
        private String comportamiento;
        private String duenio;
        private Integer edad;
        private Long usuarioId;
    }
}