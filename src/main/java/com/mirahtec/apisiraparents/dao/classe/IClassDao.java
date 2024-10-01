package com.mirahtec.apisiraparents.dao;

import com.mirahtec.apisiraparents.model.Class;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IClassDao {
    // TODO : Get all classes
    public List<Class> getAllClasses();
    // TODO : Get class by id
    public Class getClassById(int id);
}
