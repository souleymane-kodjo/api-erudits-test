package com.mirahtec.apisiraparents.dao.carnet;

import com.mirahtec.apisiraparents.model.Carnet;

import java.util.List;

public interface ICarnetDao {
    public List<Carnet> getCarnetsByStudentMatricule(String matricule);
}
