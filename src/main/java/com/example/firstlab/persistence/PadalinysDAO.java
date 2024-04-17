package com.example.firstlab.persistence;

import com.example.firstlab.entities.Padalinys;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PadalinysDAO {
    @Inject
    private EntityManager entityManager;

    public Padalinys getById(Long id) {
        return entityManager.find(Padalinys.class, id);
    }

    public List<Padalinys> getAll() {
        return entityManager.createNamedQuery("Padalinys.getAll", Padalinys.class).getResultList();
    }
    public void addEntity (Padalinys padalinys) {
        this.entityManager.persist(padalinys);
    }
}
