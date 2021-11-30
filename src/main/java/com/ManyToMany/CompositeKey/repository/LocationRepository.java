package com.ManyToMany.CompositeKey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ManyToMany.CompositeKey.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
