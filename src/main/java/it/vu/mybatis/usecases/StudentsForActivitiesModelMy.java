package it.vu.mybatis.usecases;

import it.vu.mybatis.dao.ActivitiesMapper;
import it.vu.mybatis.dao.ActivitiesStudentsMapper;
import it.vu.mybatis.dao.StudentMapper;
import it.vu.mybatis.model.Activities;
import it.vu.mybatis.model.ActivitiesStudents;
import it.vu.mybatis.model.Student;
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
public class StudentsForActivitiesModelMy {

    @Inject
    private StudentMapper studentMapper;

    @Inject
    private ActivitiesMapper activitiesMapper;

    @Inject
    private ActivitiesStudentsMapper activitiesStudentsMapper;

    @Getter
    @Setter
    private Activities activities;

    @Getter
    @Setter
    private Integer studentId;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer id = Integer.parseInt(requestParameters.get("id"));
        this.activities = activitiesMapper.selectByPrimaryKey(id);
    }

    public List<Student> getStudentsWithoutThisActivity() {
        List<Student> studentsList = studentMapper.selectAll().stream().filter(e -> e.getActivitiesList().stream().noneMatch(a -> a.getId() == this.activities.getId())).collect(Collectors.toList());
        return studentsList;
    }

    @Transactional
    public void addStudentToActivity() {
        Student student = studentMapper.selectByStudentId(studentId);
        if(student != null && student.getActivitiesList().stream().noneMatch(e -> e.getId() == this.activities.getId())) {
            ActivitiesStudents activitiesStudents = new ActivitiesStudents();
            activitiesStudents.setActivitiesId(this.activities.getId());
            activitiesStudents.setStudentsId(student.getId());
            activitiesStudentsMapper.insert(activitiesStudents);
        }
    }
}
