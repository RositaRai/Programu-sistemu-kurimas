package it.vu.mybatis.usecases;

import it.vu.mybatis.dao.ClassMapper;
import it.vu.mybatis.dao.StudentMapper;
import it.vu.mybatis.model.Class;
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
import java.util.Objects;
import java.util.stream.Collectors;

@Model
public class StudentsForClassModelMy {

    @Inject
    private ClassMapper classMapper;

    @Inject
    private StudentMapper studentMapper;
    @Getter @Setter
    private Class classRoom;

    @Getter
    @Setter
    private Integer studentId;


    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer classId = Integer.parseInt(requestParameters.get("classId"));
        this.classRoom = classMapper.selectByPrimaryKey(classId);
    }

    public List<Student> getStudentsWithoutClass() {
        List<Student> studentList = studentMapper.selectAll().stream().filter(e -> e.getClassId() == null).collect(Collectors.toList());
        return studentList;
    }

    @Transactional
    public void addStudentToClass() {
        Student student = studentMapper.selectByStudentId(studentId);
        if(student != null) {
            student.setClassId(classRoom.getId());
            student.setClassRoom(this.classRoom);
            studentMapper.updateByPrimaryKey(student);
        }
    }
}
