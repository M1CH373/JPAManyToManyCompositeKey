package com.ManyToMany.CompositeKey.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class PersonaLocationKey implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "location_Id")
	Integer locationId; 
	
	@Column(name = "persona_Id")
	Integer personaId;

	
}
