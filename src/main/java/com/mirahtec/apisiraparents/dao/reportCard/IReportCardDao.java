package com.mirahtec.apisiraparents.dao.reportCard;

import com.mirahtec.apisiraparents.model.ReportCard;

import java.util.List;

public interface IReportCardDao {
    public List<ReportCard> getReportCardsByStudentMatricule(String matricule) ;
}
