package it.vu.mybatis.usecases;

import it.vu.mybatis.dao.ClassMapper;
import it.vu.mybatis.dao.StudentMapper;
import it.vu.mybatis.model.Student;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class StudentsModelMy {

    @Inject
    private StudentMapper studentMapper;

    @Inject
    ClassMapper classMapper;

    @Getter
    private List<Student> allStudents;

    @Getter
    private List<it.vu.mybatis.model.Class> allClasses;

    @Getter
    @Setter
    private Student studentToCreate = new Student();

    private void loadAllStudents(){
        this.allStudents = studentMapper.selectAll();
    }

    private void loadAllClasses(){
        this.allClasses = classMapper.selectAll();
    }

    @PostConstruct
    public void init() {
        this.loadAllStudents();
        this.loadAllClasses();
    }

    @Transactional
    public void createStudent()
    {
        if(allStudents.stream().noneMatch(e -> e.equals(studentToCreate)))
        {
            studentMapper.insert(studentToCreate);
        }
    }
}
