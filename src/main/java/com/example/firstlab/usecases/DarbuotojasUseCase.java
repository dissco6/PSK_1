package com.example.firstlab.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.example.firstlab.entities.Darbuotojas;
import com.example.firstlab.entities.Padalinys;
import com.example.firstlab.entities.Projektas;
import com.example.firstlab.persistence.DarbuotojasDAO;
import com.example.firstlab.persistence.PadalinysDAO;
import com.example.firstlab.persistence.ProjektasDAO;
import lombok.Getter;
import lombok.Setter;
import javax.transaction.Transactional;

import java.io.Console;
import java.util.List;
import java.util.Map;

@Model
public class DarbuotojasUseCase {
    @Inject
    private DarbuotojasDAO darbuotojasDAO;
    @Inject
    private PadalinysDAO padalinysDAO;
    @Inject
    private ProjektasDAO projektasDAO;

    @Getter
    private List<Darbuotojas> allWorkers;

    @Getter
    @Setter
    private Darbuotojas darbuotojas = new Darbuotojas();

    @Setter
    @Getter
    private Long selectedDivisionId;

    @Setter
    @Getter
    private Long selectedWorkerId;

    @Setter
    @Getter
    private Padalinys division;

    @Setter
    @Getter
    private Projektas project;

    @Transactional
    public void createDarbuotojas(){
        darbuotojas.setPadalinys(padalinysDAO.getById(selectedDivisionId));
        darbuotojasDAO.addEntity(darbuotojas);
    }

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String divisionIdString = requestParameters.get("divisionId");
        String projectIdString = requestParameters.get("projectId");
        if (divisionIdString != null) {
            Long divisionId = Long.parseLong(divisionIdString);
            division = padalinysDAO.getById(divisionId);
        }
        if (projectIdString != null) {
            Long projectId = Long.parseLong(projectIdString);
            project = projektasDAO.getById(projectId);
        }
        findAllWorkers();
    }

    @Transactional
    public void addWorkerToProject(Long projectId) {
        Darbuotojas worker = darbuotojasDAO.getById(selectedWorkerId);
        project = projektasDAO.getById(projectId);
        darbuotojasDAO.addWorkerToProject(worker, project);
    }

    private void findAllWorkers() {allWorkers = darbuotojasDAO.getAll();}
}
