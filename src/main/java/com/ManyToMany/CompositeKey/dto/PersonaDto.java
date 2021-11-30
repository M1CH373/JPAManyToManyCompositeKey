package com.ManyToMany.CompositeKey.dto;

import java.util.ArrayList;
import java.util.List;

import com.ManyToMany.CompositeKey.entity.Location;
import com.ManyToMany.CompositeKey.entity.Persona;
import com.ManyToMany.CompositeKey.entity.PersonaLocation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonaDto {
	
	private Integer id;
	private String name;
	private String cognome;
	private String codiceFiscale;
	private List<Integer> locationIds;
	
	public static PersonaDto toDto(Persona persona) {
		PersonaDto pDto = new PersonaDto();
		pDto.setId(persona.getId());
		pDto.setName(persona.getName());
		pDto.setCognome(persona.getCognome());
		pDto.setCodiceFiscale(persona.getCodiceFiscale());

		
		List<PersonaLocation> locationRecuperate = persona.getLocationPresenti();
		List<Integer> ids= new ArrayList<>();

		locationRecuperate.forEach( (l) -> { 
			Location pTmp = l.getLocation();
			int id = pTmp.getId();
			ids.add(id); } );
		pDto.setLocationIds(ids);
		return pDto;
		
	}
	
	public static Persona toEntity(PersonaDto pDto) {
		Persona pEntity = new Persona();
		pEntity.setId(pDto.getId());
		pEntity.setName(pDto.getName());
		pEntity.setCognome(pDto.getCognome());
		pEntity.setCodiceFiscale(pDto.getCodiceFiscale());
		List<PersonaLocation> pL = new ArrayList<>();
		List<Integer> ids= pDto.getLocationIds();
		Persona p = new Persona();
		p.setId(pEntity.getId());
		ids.forEach( id -> {
			Location l = new Location();
			l.setId(id);
			PersonaLocation pL1 = new PersonaLocation();
			pL1.setLocation(l);
			pL1.setPersonaL(p);
			pL.add(pL1);
		});
		pEntity.setLocationPresenti(pL);
		return pEntity;
		
		
	}

}
