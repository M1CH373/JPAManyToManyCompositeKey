package com.ManyToMany.CompositeKey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManyToMany.CompositeKey.entity.Location;
import com.ManyToMany.CompositeKey.repository.LocationRepository;

@Service
public class LocationService implements ServiceInterface<Location> {

	@Autowired
	private LocationRepository locationRespository;

	public List<Location> getAll() {
		return locationRespository.findAll();
	}

	public Location getById(Integer id) {
		return locationRespository.getById(id);
	}
	
	public Location add(Location itemToBeAdded) {
		return locationRespository.save(itemToBeAdded);
	}

	public Location edit(Location itemToBeEdited) {
		Location location = locationRespository.findById(itemToBeEdited.getId()).orElseThrow();
    	if(itemToBeEdited.getNome() != null) {
    		location.setNome(itemToBeEdited.getNome());
    	}
    	return locationRespository.save(location);

	}

	public boolean delete(int id) {
		Location loc = locationRespository.findById(id).orElseThrow();
		locationRespository.delete(loc);
		return locationRespository.findById(id).isEmpty();
	}


}