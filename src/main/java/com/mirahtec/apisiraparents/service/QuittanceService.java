package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.impl.QuittanceJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Quittance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuittanceService {
    @Autowired
    private QuittanceJDBCDaoImpl quittanceJDBCDaoImpl;
    public List<Quittance> getQuittancesByStudentMatricule(String matricule) {
        return quittanceJDBCDaoImpl.getQuittancesByStudentMatricule(matricule);
    }
}
