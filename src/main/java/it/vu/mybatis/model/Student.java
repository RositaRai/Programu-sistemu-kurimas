package it.vu.mybatis.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(of = "studentid")
public class Student {
    private Integer id;
    private String name;
    private Integer studentid;
    private String surname;
    private Integer classId;

    //Added manually
    private Class classRoom;
    private List<Activities> activitiesList;
}