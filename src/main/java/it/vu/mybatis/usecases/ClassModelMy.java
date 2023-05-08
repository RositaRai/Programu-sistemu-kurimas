package it.vu.mybatis.usecases;

import it.vu.mybatis.dao.ClassMapper;
import it.vu.mybatis.model.Class;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ClassModelMy {
    @Inject
    private ClassMapper classMapper;

    @Getter
    private List<Class> allClasses;

    @Getter @Setter
    private Class classToCreate = new Class();

    @PostConstruct
    public void init() {
        this.loadAllClasses();
    }

    private void loadAllClasses() {
        this.allClasses = classMapper.selectAll();
    }

    @Transactional
    public void createClass() {
        classMapper.insert(classToCreate);
    }
}
