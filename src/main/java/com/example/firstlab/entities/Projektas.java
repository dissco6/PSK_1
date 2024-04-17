package com.example.firstlab.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@NamedQueries({
        @NamedQuery(name = "Projektas.getAll", query = "SELECT p FROM Projektas as p")
})
public class Projektas {
    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    private Date deadline;

    @Basic
    private String aprasas;

    @ManyToMany(mappedBy = "projektai")
    private List<Darbuotojas> darbuotojai;

}
