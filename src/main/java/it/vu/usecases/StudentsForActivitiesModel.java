package it.vu.usecases;

import it.vu.entities.Activities;
import it.vu.entities.Student;
import it.vu.interceptors.LoggedInvocation;
import it.vu.persistence.ActivitiesDAO;
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
public class StudentsForActivitiesModel {

    @Inject
    private StudentsDAO studentsDAO;

    @Inject
    private ActivitiesDAO activitiesDAO;

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
        this.activities = activitiesDAO.findOne(id);
    }

    @Transactional
    @LoggedInvocation
    public void addStudentToActivity() {
        Student student = studentsDAO.findByStudId(studentId);
        if(student != null && student.getActivities().stream().noneMatch(e -> e.getId() == this.activities.getId())) {
            List<Activities> activitiesList = student.getActivities();
            activitiesList.add(activities);
            student.setActivities(activitiesList);
            studentsDAO.update(student);
        }
    }

    public List<Student> getStudentsWithoutThisActivity() {
        List<Student> studentList = studentsDAO.loadAll().stream().filter(e -> e.getActivities().stream().noneMatch(a -> a.getId() == this.activities.getId())).collect(Collectors.toList());
        return studentList;
    }
}
