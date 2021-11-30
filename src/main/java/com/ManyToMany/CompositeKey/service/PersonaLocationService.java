package com.ManyToMany.CompositeKey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManyToMany.CompositeKey.entity.PersonaLocation;
import com.ManyToMany.CompositeKey.repository.PersonaLocationRepository;
@Service
public class PersonaLocationService implements ServiceInterface<PersonaLocation> {
	
	@Autowired
	private PersonaLocationRepository personaLocationRepository;

	public List<PersonaLocation> getByPersonaId(Integer id){
		return personaLocationRepository.getPersonaLocationByIdPersona(id);
	}
	
	public List<PersonaLocation> getByLocationId(Integer id){
		return personaLocationRepository.getPersonaLocationByIdLocation(id);
	}

	public void deleteByPersonaLocation(PersonaLocation pl) {
		personaLocationRepository.delete(pl);
	}

	@Override
	public PersonaLocation getById(Integer id) {
		return personaLocationRepository.getById(id);
	}

	@Override
	public PersonaLocation add(PersonaLocation itemToBeAdded) {
		return personaLocationRepository.save(itemToBeAdded);
	}

	@Override
	public boolean delete(int id) {
		PersonaLocation loc = personaLocationRepository.findById(id).orElseThrow();
		personaLocationRepository.delete(loc);
		return personaLocationRepository.findById(id).isEmpty();
	}
	
	@Override
	public List<PersonaLocation> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonaLocation edit(PersonaLocation itemToBeEdited) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
