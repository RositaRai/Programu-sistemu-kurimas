package it.vu.usecases;

import it.vu.entities.ClassRoom;
import it.vu.entities.Student;
import it.vu.interceptors.LoggedInvocation;
import it.vu.persistence.ClassesDAO;
import it.vu.persistence.StudentsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

@Model
public class StudentModel {

    @Inject
    private StudentsDAO studentsDAO;

    @Inject
    private ClassesDAO classesDAO;

    @Getter
    private List<ClassRoom> classRoomList;

    @Getter
    @Setter
    private Integer classId;

    @Getter
    @Setter
    private Student studentToCreate = new Student();

    @Getter
    private List<Student> allStudents;

    @PostConstruct
    public void init(){
        loadAllStudents();
        loadAllClasses();
    }

    @Transactional
    @LoggedInvocation
    public void createStudent()
    {
        if(classId != null)
        {
            this.studentToCreate.setClassRoom(this.classesDAO.findOne(classId));
        }

        if(allStudents.stream().noneMatch(e -> e.equals(studentToCreate)))
        {
            this.studentsDAO.persist(studentToCreate);
        }
    }

    private void loadAllStudents(){
        this.allStudents = studentsDAO.loadAll();
    }

    private void loadAllClasses(){
        this.classRoomList = classesDAO.loadAll();
    }
}
