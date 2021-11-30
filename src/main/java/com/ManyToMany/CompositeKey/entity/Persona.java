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
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personaId")
    Integer id;

    @Column(name = "nomePersona")
    String name;
    
    @Column(name = "cognomePersona")
    String cognome;
    
    @Column(name = "codiceFiscalePersona")
    String codiceFiscale;

    @OneToMany(mappedBy = "personaL" , cascade = CascadeType.REMOVE)
    private List<PersonaLocation> locationPresenti;
            

}
