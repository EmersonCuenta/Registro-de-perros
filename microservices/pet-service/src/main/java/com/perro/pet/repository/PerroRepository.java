package com.perro.pet.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.perro.pet.model.Perro;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PerroRepository extends ReactiveCrudRepository<Perro, Long> {
    Flux<Perro> findAllByUsuarioId(Long usuarioId);
    Mono<Perro> findByIdAndUsuarioId(Long id, Long usuarioId);
}