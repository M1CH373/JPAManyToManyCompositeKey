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

import com.ManyToMany.CompositeKey.dto.LocationDto;
import com.ManyToMany.CompositeKey.entity.Location;
import com.ManyToMany.CompositeKey.entity.PersonaLocation;
import com.ManyToMany.CompositeKey.service.LocationService;
import com.ManyToMany.CompositeKey.service.PersonaLocationService;
import com.ManyToMany.CompositeKey.service.PersonaService;

@RestController
@RequestMapping("/location")
public class LocationController {
	
    @Autowired
    private LocationService locationService;
    
    @Autowired
    private PersonaLocationService personaLocationService;
    
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<?> getAll() {
    	List<LocationDto> location = locationService.getAll().stream()
    		.map(LocationDto::toDto)
  			.collect(Collectors.toList());
          return new ResponseEntity<>(location, HttpStatus.OK);

    }
    
    @PostMapping
    public ResponseEntity<?> edit(@RequestBody LocationDto locationDto) {
    	Location l = LocationDto.toEntity(locationDto);
    	if(locationDto.getPersoneIds() != null) {
    		for(PersonaLocation pl : personaLocationService.getByLocationId((locationDto.getId()))) {
    			if(!locationDto.getPersoneIds().contains(pl.getPersonaL().getId())){
    				personaLocationService.deleteByPersonaLocation(pl);
    			}
    		}
    		for(Integer id : locationDto.getPersoneIds()) {
     			PersonaLocation personaLocation = new PersonaLocation(l.getId() , id);
    			personaLocation.setPersonaL(personaService.getById(id));
    			personaLocation.setLocation(locationService.getById(locationDto.getId()));
    			personaLocationService.add(personaLocation);
    		}
    	
    	}

    	Location pNew = locationService.edit(l);
    	LocationDto newDto = LocationDto.toDto(pNew);
        return new ResponseEntity<>(newDto, HttpStatus.OK);	 
    	    	
    }

    @PutMapping
    public ResponseEntity<?> add(@RequestBody LocationDto locationDto) {
    	Location l = locationService.add(LocationDto.toEntity(locationDto));
    	if(locationDto.getPersoneIds() != null) {
    		List<PersonaLocation> persLoc = new ArrayList<PersonaLocation>();
    		for(Integer id : locationDto.getPersoneIds()) {

     			PersonaLocation personaLocation = new PersonaLocation( l.getId() , id );
     			personaLocation.setLocation(locationService.getById(l.getId()));
     			personaLocation.setPersonaL(personaService.getById(id));
    			personaLocationService.add(personaLocation);
    			persLoc.add(personaLocation);
    			
    		}
    		l.setPersonePresenti(persLoc);
    		locationService.edit(l);
    	}

    	LocationDto newlDto = LocationDto.toDto(l);
        return new ResponseEntity<>(newlDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
    	return new ResponseEntity<>(locationService.delete(id), HttpStatus.OK);
    }

    
}
