package com.ManyToMany.CompositeKey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManyToMany.CompositeKey.entity.Persona;
import com.ManyToMany.CompositeKey.repository.PersonaRepository;

@Service
public class PersonaService implements ServiceInterface<Persona>{

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> getAll() {
        return personaRepository.findAll();
    }
    
    public Persona getById (Integer id) {
    	return personaRepository.getById(id);
    }

    public Persona edit(Persona itemToBeEdited) {
    	Persona pers = personaRepository.findById(itemToBeEdited.getId()).orElseThrow();
    	if(itemToBeEdited.getName() != null) {
    		pers.setName(itemToBeEdited.getName());
    	}
    	if(itemToBeEdited.getCognome() != null) {
    		pers.setCognome(itemToBeEdited.getCognome());
    	}
    	if(itemToBeEdited.getCodiceFiscale() != null) {
    		pers.setCodiceFiscale(itemToBeEdited.getCodiceFiscale());
    	}  	
    	return personaRepository.save(pers);
    	 
    }

    public Persona add(Persona itemToBeEdited) {
    	return personaRepository.save(itemToBeEdited);

    }

    public boolean delete(int id) {
    	Persona pers = personaRepository.findById(id).orElseThrow();
    	personaRepository.delete(pers);
        return personaRepository.findById(id).isEmpty();
    }

}
