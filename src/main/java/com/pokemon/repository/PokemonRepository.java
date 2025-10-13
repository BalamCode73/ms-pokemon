package com.pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemon.model.entities.Pokemon;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    // Buscar por id de pokédex
    Optional<Pokemon> findByIdEquals(Long id);
}