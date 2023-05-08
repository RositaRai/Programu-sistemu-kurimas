package it.vu.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Activities.findAll", query = "select a from Activities as a")
})
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Activities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private String teacher;

    @EqualsAndHashCode.Include
    @Basic(optional = false)
    private String name;

    @Basic
    private String description;

    @ManyToMany(mappedBy = "activities")
    private List<Student> students = new ArrayList<>();
}
