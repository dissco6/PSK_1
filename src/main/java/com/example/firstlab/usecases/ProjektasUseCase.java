package com.example.firstlab.usecases;

import com.example.firstlab.entities.Darbuotojas;
import com.example.firstlab.entities.Projektas;
import com.example.firstlab.persistence.DarbuotojasDAO;
import com.example.firstlab.persistence.ProjektasDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class ProjektasUseCase {
    @Inject
    private ProjektasDAO projektasDAO;
    @Inject
    private DarbuotojasDAO darbuotojasDAO;

    @Getter
    private List<Projektas> allProjects;

    @Getter
    @Setter
    private Projektas projektas = new Projektas();

    @Getter
    @Setter
    private Darbuotojas worker;

    @Setter
    @Getter
    private Long selectedProjectId;

    @Transactional
    public void createProjektas(){
        projektasDAO.addEntity(projektas);
    }

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String workerIdString = requestParameters.get("workerId");
        if (workerIdString != null) {
            Long workerId = Long.parseLong(workerIdString);
            worker = darbuotojasDAO.getById(workerId);
        }
        findAllProjects();
    }

    @Transactional
    public void addProjectToWorker(Long workerId) {
        worker = darbuotojasDAO.getById(workerId);
        Projektas project = projektasDAO.getById(selectedProjectId);
        projektasDAO.addProjectToWorker(worker, project);
    }

    private void findAllProjects() {allProjects = projektasDAO.getAll();}
}
