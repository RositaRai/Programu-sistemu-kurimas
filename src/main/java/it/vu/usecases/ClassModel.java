package it.vu.usecases;

import it.vu.decorator.NotificationInterface;
import it.vu.interceptors.LoggedInvocation;
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
    @LoggedInvocation
    public String createClass(){
        if(allClasses.stream().noneMatch(e -> e.equals(classToCreate)))
        {
            this.classesDAO.persist(classToCreate);
            return "classList?faces-redirect=true&amp;" + "&error=notification";
        }
        return "classList?faces-redirect=true";
    }

    private void loadAllClasses(){
        this.allClasses = classesDAO.loadAll();
    }


    @Inject
    NotificationInterface notificationInterface;

    public String getNotification() {
        return this.notificationInterface.sentNotification("klasę!");
    }
}
