package com.ManyToMany.CompositeKey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ManyToMany.CompositeKey.entity.PersonaLocation;

@Repository
public interface PersonaLocationRepository extends JpaRepository<PersonaLocation, Integer>{

	@Query("SELECT pl FROM PersonaLocation pl WHERE pl.personaL.id = :id")
	public List<PersonaLocation> getPersonaLocationByIdPersona(@Param("id") Integer id);
	
	@Query("SELECT pl FROM PersonaLocation pl WHERE pl.location.id = :id")
	public List<PersonaLocation> getPersonaLocationByIdLocation(@Param("id") Integer id);
	
}
