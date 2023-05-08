package it.vu.mybatis.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(of = "name")
public class Activities {
    private Integer id;
    private String description;
    private String name;
    private String teacher;

    //Added manually
    private List<Student> students;

}