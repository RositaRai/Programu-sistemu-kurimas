package it.vu.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import it.vu.entities.ClassRoom;

import java.util.List;

@ApplicationScoped
public class ClassesDAO {

    @Inject
    private EntityManager em;

    public List<ClassRoom> loadAll() {
        return em.createNamedQuery("Class.findAll", ClassRoom.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(ClassRoom classInfo){
        this.em.persist(classInfo);
    }

    public ClassRoom findOne(Integer id) {
        return em.find(ClassRoom.class, id);
    }

    public ClassRoom findByName(String name)
    {
        return em.createQuery("select c from ClassRoom as c where c.className = :name", ClassRoom.class).setParameter("name", name).getResultList()
                .stream().findFirst().orElse(null);
    }
}
