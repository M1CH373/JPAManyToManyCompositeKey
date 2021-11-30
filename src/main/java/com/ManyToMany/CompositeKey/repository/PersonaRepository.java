package com.ManyToMany.CompositeKey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ManyToMany.CompositeKey.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
