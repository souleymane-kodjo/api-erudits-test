package com.mirahtec.apisiraparents.service.evaluationService;

import com.mirahtec.apisiraparents.dao.evaluation.EvaluationJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {
    @Autowired
    private EvaluationJDBCDaoImpl noteJDBCDao;
    public List<Evaluation> getEvaluationsByStudentClassID(String classID) {
        return noteJDBCDao.getEvaluationsByStudentclassID(classID);
    }
}
