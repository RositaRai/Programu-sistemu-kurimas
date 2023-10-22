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
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Model
public class StudentsForClassModel {
    @Inject
    private ClassesDAO classesDAO;

    @Inject
    private StudentsDAO studentsDAO;

    @Getter
    @Setter
    private ClassRoom classRoom;

    @Getter
    @Setter
    private Integer studentId;


    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer classId = Integer.parseInt(requestParameters.get("id"));
        this.classRoom = classesDAO.findOne(classId);
    }

    @Transactional
    @LoggedInvocation
    public void addStudentToClass() {
        Student student = studentsDAO.findByStudId(studentId);
        if(student != null) {
            student.setClassRoom(this.classRoom);
            studentsDAO.update(student);
        }
    }

    public List<Student> getStudentsWithoutClass() {
        List<Student> studentList = studentsDAO.loadAll().stream().filter(e -> e.getClassRoom() == null).collect(Collectors.toList());
        return studentList;
    }
}
