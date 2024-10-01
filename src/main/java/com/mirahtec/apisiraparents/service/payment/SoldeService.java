package com.mirahtec.apisiraparents.service.payment;

import com.mirahtec.apisiraparents.dao.solde.SoldesJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Payment;
import com.mirahtec.apisiraparents.model.Solde;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SoldeService {
    @Autowired
    SoldesJDBCDaoImpl soldesJDBCDao;
    public List<Solde> getSoldesByStudentMatricule(String matricule) {
        try{
            return soldesJDBCDao.getSoldesByStudentMatricule(matricule);
        }catch (Exception e){
            log.error("Erreur de récupération des soldes", e);
            return null;
        }
    }
}
