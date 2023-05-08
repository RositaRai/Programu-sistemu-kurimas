package it.vu.persistence;

import it.vu.entities.ClassRoom;
import it.vu.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class StudentsDAO {
    @Inject
    private EntityManager em;

    public List<Student> loadAll() {
        return em.createNamedQuery("Student.findAll", Student.class).getResultList();
    }

    public Student findByStudId(Integer id)
    {
        return em.createQuery("select s from Student as s where s.studentId = :id", Student.class).setParameter("id", id).getResultList()
            .stream().findFirst().orElse(null);
    }

    public void persist(Student student){
        this.em.persist(student);
    }

    public Student findOne(Integer id){
        return em.find(Student.class, id);
    }

    public Student update(Student student){
        return em.merge(student);
    }
}
