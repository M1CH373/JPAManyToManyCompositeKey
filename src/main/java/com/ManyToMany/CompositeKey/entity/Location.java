package com.ManyToMany.CompositeKey.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationId")
    Integer id;

    @Column(name = "nomeLocation")
    String nome;

    @OneToMany(mappedBy = "location", cascade = CascadeType.REMOVE)
    private List<PersonaLocation> personePresenti;

    @OneToMany(mappedBy = "location", cascade = CascadeType.REMOVE)
    private List<Spettacolo> spettacoloPresenti;
    
}