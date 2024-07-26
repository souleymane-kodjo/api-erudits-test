package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.impl.CarnetJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Carnet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarnetService {
    @Autowired
    CarnetJDBCDaoImpl carnetJDBCDao;

    public List<Carnet> getCarnetsByStudentMatricule(String matricule) {
        return carnetJDBCDao.getCarnetsByStudentMatricule(matricule);
    }
}
