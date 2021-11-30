package com.ManyToMany.CompositeKey.dto;
import java.util.Date;

import com.ManyToMany.CompositeKey.entity.Location;
import com.ManyToMany.CompositeKey.entity.Spettacolo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpettacoloDto {

	private int id;
	private String nome;
	private Date data;
	private int locationRef;
	
	
	public static SpettacoloDto toDto (Spettacolo spettacolo) {
		SpettacoloDto sDto = new SpettacoloDto();
		sDto.setId(spettacolo.getId());
		sDto.setNome(spettacolo.getNome());
		sDto.setData(spettacolo.getData());

		Location l = spettacolo.getLocation();
		sDto.setLocationRef(l.getId());

		return sDto;
	}
	
	public static Spettacolo toEntity (SpettacoloDto sDto) {
		Spettacolo spettacolo = new Spettacolo();
		spettacolo.setId(sDto.getId());
		spettacolo.setNome(sDto.getNome());
		spettacolo.setData(sDto.getData());
		
		Location l = new Location();
		int idLocation = sDto.getLocationRef();
		l.setId(idLocation);
		spettacolo.setLocation(l);
		return spettacolo;
		
	}
	
}
