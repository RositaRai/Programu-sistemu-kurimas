package it.vu.usecases;

import it.vu.entities.Activities;
import it.vu.interceptors.LoggedInvocation;
import it.vu.persistence.ActivitiesDAO;
import it.vu.specialize.DescriptionInterface;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ActivitiesModel {

    @Inject
    private ActivitiesDAO activitiesDAO;

    @Inject
    private DescriptionInterface description;

    @Getter
    @Setter
    private Activities activitiesToCreate = new Activities();

    @Getter
    private List<Activities> allActivities;

    @PostConstruct
    public void init(){
        loadAllActivities();
    }

    @Transactional
    @LoggedInvocation
    public void createActivities(){
        if(allActivities.stream().noneMatch(e -> e.equals(activitiesToCreate)))
        {
            this.activitiesToCreate.setDescription(description.generateDescription());
            this.activitiesDAO.persist(activitiesToCreate);
        }
    }

    private void loadAllActivities(){
        this.allActivities = activitiesDAO.loadAll();
    }

}
