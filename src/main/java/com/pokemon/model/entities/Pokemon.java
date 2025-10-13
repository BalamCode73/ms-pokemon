package com.pokemon.model.entities;

import com.pokemon.model.StringListConverter;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPokemon;
    private Long id;
    private String name;
    private String image;



    @Convert(converter = StringListConverter.class)
    private List<String> types;


    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

	public Long getIdPokemon() {
		return idPokemon;
	}

	public void setIdPokemon(Long idPokemon) {
		this.idPokemon = idPokemon;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}





  
    

}
