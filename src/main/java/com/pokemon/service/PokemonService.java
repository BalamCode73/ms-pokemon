package com.pokemon.service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import com.pokemon.model.api.PokemonRequest;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.model.api.PokemonResponse;
import com.pokemon.model.entities.Pokemon;
import com.pokemon.repository.PokemonRepository;

import jakarta.annotation.PostConstruct;

@Service

public class PokemonService {

	@Autowired
	PokemonRepository repository;
     ObjectMapper mapperObjet= new ObjectMapper();
     private ModelMapper modelMapper = new ModelMapper();


     	@PostConstruct
	    @Transactional
	    public void loadPokemonData() {
	        try {
				List<PokemonResponse>  listPokemon=getPokemonData();
				if(listPokemon.isEmpty()){
	           InputStream is = getClass().getResourceAsStream("/data/pokemon.json");
	           List<Pokemon> pokemons = mapperObjet.readValue(is, new TypeReference<List<Pokemon>>() {});
	           repository.saveAll(pokemons);
				}
	        } catch (Exception e) {
	            throw new RuntimeException("Error al cargar datos de Pokémon", e);
	        }
	    }

        public List<PokemonResponse>  getPokemonData() {
           return  modelMapper.map(repository.findAll(), new TypeToken<List<PokemonResponse>>(){}.getType());

        }


	public PokemonResponse savePokemon(PokemonRequest pokemonReq) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Pokemon pokeEnt=	modelMapper.map(pokemonReq,  Pokemon.class);
		return  modelMapper.map(repository.save(pokeEnt),PokemonResponse.class );

	}

	public PokemonResponse updatePokemon(PokemonRequest pokemonReq) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Optional<Pokemon> pokemonUp=repository.findByIdEquals(pokemonReq.getId());
		pokemonUp.ifPresentOrElse(pokemon->{
					Pokemon pokeEnt=	modelMapper.map(pokemonReq,  Pokemon.class);
					pokeEnt.setIdPokemon(pokemon.getIdPokemon());
					repository.save(pokeEnt);
				},
				()->{
					throw new EntityNotFoundException("No existe el Pokémon con ID: " + pokemonReq.getId());
				});
		return  modelMapper.map(pokemonReq,PokemonResponse.class);

	}
	    
}
