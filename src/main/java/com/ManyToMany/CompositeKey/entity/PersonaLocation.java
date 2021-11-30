package com.ManyToMany.CompositeKey.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PersonaLocation {

	@EmbeddedId
	PersonaLocationKey id;

	public PersonaLocation(Integer location, Integer personaL){
		this.id = new PersonaLocationKey(location, personaL);
	}

    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name = "location_Id")
    Location location;

    @ManyToOne
    @MapsId("personaId")
    @JoinColumn(name = "persona_Id")
    Persona personaL;
    

}