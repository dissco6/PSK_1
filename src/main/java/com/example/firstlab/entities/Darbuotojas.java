package com.example.firstlab.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@EqualsAndHashCode
@NamedQueries({
        @NamedQuery(name = "Darbuotojas.getAll", query = "SELECT d FROM Darbuotojas as d")
})
public class Darbuotojas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String vardas;

    @Basic(optional = false)
    private String pavarde;


    @ManyToOne
    private Padalinys padalinys;

    @ManyToMany
    private List<Projektas> projektai;

}
