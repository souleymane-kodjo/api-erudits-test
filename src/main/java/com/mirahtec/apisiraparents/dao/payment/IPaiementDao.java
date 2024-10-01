package com.mirahtec.apisiraparents.dao;

import com.mirahtec.apisiraparents.model.Evaluation;

import java.util.List;

public interface IPaiementDao {
    public List<Evaluation> getEvaluationsByStudentclassID(String matricule);
}
