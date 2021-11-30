package com.ManyToMany.CompositeKey.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManyToMany.CompositeKey.entity.Spettacolo;
import com.ManyToMany.CompositeKey.repository.SpettacoloRepository;

@Service
public class SpettacoloService implements ServiceInterface<Spettacolo> {

	@Autowired
	private SpettacoloRepository spettacoloRepository;
	
	@Override
	public List<Spettacolo> getAll() {
		return spettacoloRepository.findAll();
	}

	@Override
	public Spettacolo getById(Integer id) {
		return spettacoloRepository.getById(id);
	}

	@Override
	public Spettacolo add(Spettacolo itemToBeAdded) {
		return spettacoloRepository.save(itemToBeAdded);
	}

	@Override
	public Spettacolo edit(Spettacolo itemToBeEdited) {
		Spettacolo spettacolo = spettacoloRepository.findById(itemToBeEdited.getId()).orElseThrow();
		if(spettacolo.getNome() != null) {
			spettacolo.setNome(itemToBeEdited.getNome());
	    }
	    if(spettacolo.getData() != null) {
	    	spettacolo.setData(itemToBeEdited.getData());
	    }
	    if(spettacolo.getLocation() != null) {
	    	spettacolo.setLocation(itemToBeEdited.getLocation());
	    }
	    return spettacoloRepository.save(spettacolo);
	}

	@Override
	public boolean delete(int id) {
		Spettacolo spettacolo = spettacoloRepository.findById(id).orElseThrow();
		spettacoloRepository.delete(spettacolo);
		return spettacoloRepository.findById(id).isEmpty();
	}
	
	public List<Spettacolo> findByData(Date dataFirst, Date dataLast){
		List<Spettacolo> listSpettacolo = spettacoloRepository.findByDataBetween(dataFirst, dataLast);
		return listSpettacolo;
	}

}
