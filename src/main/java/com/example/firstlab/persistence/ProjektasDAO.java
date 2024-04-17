package com.example.firstlab.persistence;

import com.example.firstlab.entities.Darbuotojas;
import com.example.firstlab.entities.Padalinys;
import com.example.firstlab.entities.Projektas;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ProjektasDAO {
    @Inject
    private EntityManager entityManager;

    public Darbuotojas getWorkerById(Long id) {
        return entityManager.find(Darbuotojas.class, id);
    }

    public Projektas getById(Long id) {
        return entityManager.find(Projektas.class, id);
    }


    public List<Projektas> getAll() {
        return entityManager.createNamedQuery("Projektas.getAll", Projektas.class).getResultList();
    }
    public void addEntity (Projektas projektas) {
        this.entityManager.persist(projektas);
    }

    public void addProjectToWorker(Darbuotojas worker, Projektas project) {
        worker.getProjektai().add(project);
        entityManager.merge(worker);
    }
}
