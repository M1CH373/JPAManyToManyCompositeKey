package com.ManyToMany.CompositeKey.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ManyToMany.CompositeKey.dto.PersonaDto;
import com.ManyToMany.CompositeKey.entity.Persona;
import com.ManyToMany.CompositeKey.entity.PersonaLocation;
import com.ManyToMany.CompositeKey.service.LocationService;
import com.ManyToMany.CompositeKey.service.PersonaLocationService;
import com.ManyToMany.CompositeKey.service.PersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;
    
    @Autowired
    private PersonaLocationService personaLocationService;

    @Autowired
    private LocationService locationService;
    
    @GetMapping
    public ResponseEntity<?> getAll() {
    	List<PersonaDto> persona = personaService.getAll().stream()
    			.map(PersonaDto::toDto)
    			.collect(Collectors.toList());
    	
    	
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> edit(@RequestBody PersonaDto personaDto) {
    	Persona p = PersonaDto.toEntity(personaDto);
    	if(personaDto.getLocationIds() != null) {
    		for(PersonaLocation pl : personaLocationService.getByPersonaId(personaDto.getId())) {
    			if(!personaDto.getLocationIds().contains(pl.getLocation().getId())){
    				personaLocationService.deleteByPersonaLocation(pl);
    			}
    		}
    		for(Integer id : personaDto.getLocationIds()) {
     			PersonaLocation personaLocation = new PersonaLocation(id , p.getId());
    			personaLocation.setPersonaL(personaService.getById(personaDto.getId()));
    			personaLocation.setLocation(locationService.getById(id));
    			personaLocationService.add(personaLocation);
    		}
    	
    	
    	}
    	
    	Persona pNew = personaService.edit(p);
    	PersonaDto newDto = PersonaDto.toDto(pNew);
        return new ResponseEntity<>(newDto, HttpStatus.OK);	    	
    }

    @PutMapping
    public ResponseEntity<?> add(@RequestBody PersonaDto personaDto) {
    	Persona p = personaService.add(PersonaDto.toEntity(personaDto));
    	if(personaDto.getLocationIds() != null) {
    		List<PersonaLocation> persLoc = new ArrayList<PersonaLocation>();
    		for(Integer id : personaDto.getLocationIds()) {

     			PersonaLocation personaLocation = new PersonaLocation(id , p.getId());
     			personaLocation.setLocation(locationService.getById(id));
     			personaLocation.setPersonaL(personaService.getById(p.getId()));
    			personaLocationService.add(personaLocation);
    			persLoc.add(personaLocation);
    			
    		}
    		p.setLocationPresenti(persLoc);
    		personaService.edit(p);
    	}

    	
    	PersonaDto newDto = PersonaDto.toDto(p);
        return new ResponseEntity<>(newDto, HttpStatus.OK);
    	    	
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(personaService.delete(id), HttpStatus.OK);
    }
    
    
	
}