package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.impl.SoldesJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldeService {
    @Autowired
    SoldesJDBCDaoImpl soldesJDBCDao;
    public List<Payment> getSoldesByStudentMatricule(String matricule) {
        try{
            return soldesJDBCDao.getSoldesByStudentMatricule(matricule);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
