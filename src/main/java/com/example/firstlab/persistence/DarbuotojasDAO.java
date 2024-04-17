package com.example.firstlab.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.example.firstlab.entities.Darbuotojas;
import com.example.firstlab.entities.Padalinys;
import com.example.firstlab.entities.Projektas;

import java.util.List;

@ApplicationScoped
public class DarbuotojasDAO {
    @Inject
    private EntityManager entityManager;

    public Darbuotojas getById(Long id) {
        return entityManager.find(Darbuotojas.class, id);
    }

    public List<Darbuotojas> getAll() {
        return entityManager.createNamedQuery("Darbuotojas.getAll", Darbuotojas.class).getResultList();
    }
    public void addEntity (Darbuotojas darbuotojas) {
        this.entityManager.persist(darbuotojas);
    }

    public void addWorkerToProject(Darbuotojas worker, Projektas project) {
        worker.getProjektai().add(project);
        entityManager.merge(worker);
    }
}
