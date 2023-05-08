package it.vu.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select s from Student as s")
})
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "studentId")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String surname;

    @Basic(optional = false)
    private Integer studentId;

    @ManyToOne
    @JoinColumn(name="CLASS_ID")
    private ClassRoom classRoom;

    @ManyToMany
    @JoinTable(name="ACTIVITIES_STUDENTS")
    private List<Activities> activities = new ArrayList<>();
}
