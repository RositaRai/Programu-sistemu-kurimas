package it.vu.persistence;

import it.vu.entities.Activities;
import it.vu.entities.ClassRoom;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ActivitiesDAO {

    @Inject
    private EntityManager em;

    public List<Activities> loadAll() {
        return em.createNamedQuery("Activities.findAll", Activities.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Activities activities){
        this.em.persist(activities);
    }

    public Activities findOne(Integer id) {
        return em.find(Activities.class, id);
    }
}
