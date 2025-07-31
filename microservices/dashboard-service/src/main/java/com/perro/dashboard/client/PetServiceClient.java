package com.perro.dashboard.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.perro.dashboard.model.Perro;

@FeignClient(name = "pet-service")
public interface PetServiceClient {
    
    @GetMapping("/api/perros")
    List<Perro> listarPerros(@RequestHeader("Authorization") String authHeader);
}