package it.vu.usecases;

import it.vu.persistence.ClassesDAO;
import it.vu.entities.ClassRoom;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ClassModel {
    @Inject
    private ClassesDAO classesDAO;

    @Getter
    @Setter
    private ClassRoom classToCreate = new ClassRoom();

    @Getter
    private List<ClassRoom> allClasses;

    @PostConstruct
    public void init(){
        loadAllClasses();
    }

    @Transactional
    public void createClass(){
        if(allClasses.stream().noneMatch(e -> e.equals(classToCreate)))
        {
            this.classesDAO.persist(classToCreate);
        }
    }

    private void loadAllClasses(){
        this.allClasses = classesDAO.loadAll();
    }
}
