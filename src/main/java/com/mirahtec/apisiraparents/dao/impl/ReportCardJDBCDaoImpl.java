package com.mirahtec.apisiraparents.dao.impl;

import com.mirahtec.apisiraparents.dao.IReportCardDao;
import com.mirahtec.apisiraparents.model.ReportCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ReportCardJDBCDaoImpl implements IReportCardDao {
    @Autowired
    private JdbcTemplate beanJDBCTemplate;

    @Override
    public List<ReportCard> getReportCardsByStudentMatricule(String matricule) {
        try{
            String sql = "SELECT * FROM documents_eleves WHERE typeDocument = 'bulletin' AND matriculeEleve = ? AND public = '1'";
            return beanJDBCTemplate.query(sql, new Object[]{matricule}, new RowMapper<ReportCard>() {
                @Override
                public ReportCard mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportCard reportCard = new ReportCard();
                    reportCard.setMatricule(rs.getString("matriculeEleve"));
                    reportCard.setPublie(rs.getString("public"));
                    reportCard.setDate(rs.getString("dateDocument"));
                    reportCard.setPj(rs.getString("pj"));
                    reportCard.setType(rs.getString("typeDocument"));
                    return reportCard;
                }
            });

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}