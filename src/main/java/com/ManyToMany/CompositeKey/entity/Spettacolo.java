package com.ManyToMany.CompositeKey.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "spettacolo")
public class Spettacolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSpettacolo")
	private Integer id;
	
	@Column(name = "nomeSpettacolo")
	private String nome;
	
	@Column(name = "dataSpettacolo")
	private Date data;
	
    @ManyToOne
    @JoinColumn(name = "locationRef", referencedColumnName = "locationId" )
    private Location location;
	
	
}
