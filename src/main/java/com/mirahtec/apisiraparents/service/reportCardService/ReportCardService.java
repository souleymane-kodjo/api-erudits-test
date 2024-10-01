package com.mirahtec.apisiraparents.service.reportCardService;

import com.mirahtec.apisiraparents.dao.reportCard.ReportCardJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.ReportCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class ReportCardService {
    @Autowired
    private ReportCardJDBCDaoImpl reportCardJDBCDao;
    public List<ReportCard> getReportCardsByStudentMatricule(String matricule) {
       return reportCardJDBCDao.getReportCardsByStudentMatricule(matricule);
    }
}
