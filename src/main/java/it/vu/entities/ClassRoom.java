package it.vu.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({
        @NamedQuery(name = "Class.findAll", query = "select c from ClassRoom as c")
})
@Table(name = "CLASS")
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private String teacher;

    @EqualsAndHashCode.Include
    @Basic(optional = false)
    private String className;

    @OneToMany(mappedBy = "classRoom")
    private List<Student> students = new ArrayList<>();
}
