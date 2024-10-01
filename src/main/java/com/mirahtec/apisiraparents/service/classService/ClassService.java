package com.mirahtec.apisiraparents.service.classService;

import com.mirahtec.apisiraparents.dao.classe.ClassJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Class;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClassService {
    @Autowired
    private ClassJDBCDaoImpl classJDBCDao;

    public List<Class> getAllClasses() {
        List<Class> classes ;
        try {
            log.info("Getting all classes");
            return classes = classJDBCDao.getAllClasses();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Class getClassById(Integer idClasse) {
        try {
            log.info("Getting class by id: {}", idClasse);
            return classJDBCDao.getClassById(idClasse);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
