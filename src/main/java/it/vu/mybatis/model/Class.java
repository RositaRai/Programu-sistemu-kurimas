package it.vu.mybatis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Class {
    private Integer id;
    private String classname;
    private String teacher;

    //Added manually
    private List<Student> students;
}