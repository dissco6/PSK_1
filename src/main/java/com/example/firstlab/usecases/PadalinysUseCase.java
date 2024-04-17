package com.example.firstlab.usecases;

import com.example.firstlab.entities.Padalinys;
import com.example.firstlab.persistence.PadalinysDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PadalinysUseCase {
    @Inject
    private PadalinysDAO padalinysDAO;

    @Getter
    private List<Padalinys> allDivisions;

    @Getter
    @Setter
    private Padalinys padalinys = new Padalinys();

    @Transactional
    public void createPadalinys(){
        padalinysDAO.addEntity(padalinys);
    }

    @PostConstruct
    public void init() {findAllDivisions();}

    private void findAllDivisions() {allDivisions = padalinysDAO.getAll();}
}
