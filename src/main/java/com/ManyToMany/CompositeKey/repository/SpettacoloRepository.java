package com.ManyToMany.CompositeKey.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ManyToMany.CompositeKey.entity.Spettacolo;

public interface SpettacoloRepository extends JpaRepository<Spettacolo, Integer>{

	public List<Spettacolo> findByDataBetween (Date dataFirst, Date dataLast);
}
