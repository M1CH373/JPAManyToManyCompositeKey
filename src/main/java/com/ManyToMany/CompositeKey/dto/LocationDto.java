package com.ManyToMany.CompositeKey.dto;

import java.util.ArrayList;
import java.util.List;

import com.ManyToMany.CompositeKey.entity.Location;
import com.ManyToMany.CompositeKey.entity.Persona;
import com.ManyToMany.CompositeKey.entity.PersonaLocation;
import com.ManyToMany.CompositeKey.entity.Spettacolo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LocationDto {
	
	private Integer id;
	private String nome;
	private List<Integer> personeIds;
	private List<Integer> spettacoloIds;
	
	public static LocationDto toDto (Location l) {
		LocationDto lDto = new LocationDto();
		lDto.setId(l.getId());
		lDto.setNome(l.getNome());
		List<PersonaLocation> personeRecuperate = l.getPersonePresenti();
		List<Spettacolo> spettacoloRecuperato = l.getSpettacoloPresenti();
		List<Integer> idsPersone= new ArrayList<>();
		List<Integer> idsSpettacoli= new ArrayList<>();
		
		personeRecuperate.forEach( (pL) -> {
			Persona pNews = pL.getPersonaL();
			int idP = pNews.getId();
			idsPersone.add(idP); } );
		spettacoloRecuperato.forEach( (pL) -> {
			int idS = pL.getId();
			idsSpettacoli.add(idS);
			} );
		lDto.setPersoneIds(idsPersone);
		lDto.setSpettacoloIds(idsSpettacoli);
		return lDto;
		
	}
	
	public static Location toEntity(LocationDto lDto) {
		Location lEntity = new Location();
		lEntity.setId(lDto.getId());
		lEntity.setNome(lDto.getNome());
		List<PersonaLocation> pL = new ArrayList<>();
		List<Integer> idsPersone= lDto.getPersoneIds();
		List<Spettacolo> spettacolo = new ArrayList<>();
		List<Integer> idsSpettacolo= lDto.getSpettacoloIds();
		Location l = new Location();
		l.setId(lEntity.getId());
		idsPersone.forEach( id -> {
			Persona p = new Persona();
			p.setId(id);
			PersonaLocation pL1 = new PersonaLocation();
			pL1.setPersonaL(p);
			pL1.setLocation(l);
			pL.add(pL1);
			System.out.println();
		});
		idsSpettacolo.forEach( id -> {
			Spettacolo s = new Spettacolo();
			s.setId(id);
			spettacolo.add(s);
		});
		lEntity.setPersonePresenti(pL);
		lEntity.setSpettacoloPresenti(spettacolo);
		return lEntity;
		
		
	}


}
