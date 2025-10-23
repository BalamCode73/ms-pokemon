package com.pokemon.controller;


import java.util.List;

import com.pokemon.model.api.PokemonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pokemon.model.api.PokemonResponse;
import com.pokemon.service.PokemonService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping()
    public List<PokemonResponse> getPokemons() {
        return pokemonService.getPokemonData();
    }
    
    @GetMapping("/recharge")
    public ResponseEntity<?> rechargePokemons() {
         pokemonService.loadPokemonData();
         return ResponseEntity.ok("Ok");
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePokemons(@RequestBody PokemonRequest pokemonRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonService.savePokemon(pokemonRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePokemons(@RequestBody PokemonRequest pokemonRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonService.updatePokemon(pokemonRequest));
    }

    @DeleteMapping("/delete/{idPokemon}")
    public ResponseEntity<?> deletePokemons(@PathVariable long idPokemon) {
        return ResponseEntity.status(HttpStatus.OK).body(pokemonService.deletePokemon(idPokemon));
    }
}
