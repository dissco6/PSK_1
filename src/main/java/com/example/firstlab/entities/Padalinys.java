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
        @NamedQuery(name = "Padalinys.getAll", query = "SELECT p FROM Padalinys as p")
})
public class Padalinys {
    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String pavadinimas;

    @Basic(optional = false)
    private String adresas;

    @OneToMany(mappedBy = "padalinys")
    private List<Darbuotojas> darbuotojai;

}
