package it.vu.mybatis.usecases;

import it.vu.mybatis.dao.ActivitiesMapper;
import it.vu.mybatis.model.Activities;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ActivitiesModelMy {

    @Inject
    private ActivitiesMapper activitiesMapper;

    @Getter
    private List<Activities> allActivities;

    @Getter
    @Setter
    private Activities activitiesToCreate = new Activities();

    private void loadAllActivities() {
        this.allActivities = activitiesMapper.selectAll();
    }

    @PostConstruct
    public void init() {
        this.loadAllActivities();
    }

    @Transactional
    public void createActivities() {
        if(allActivities.stream().noneMatch(e -> e.equals(activitiesToCreate))){
            this.activitiesMapper.insert(activitiesToCreate);
        }
    }

}
