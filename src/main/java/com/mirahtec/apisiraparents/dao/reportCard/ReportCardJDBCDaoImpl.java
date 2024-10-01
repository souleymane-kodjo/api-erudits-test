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
                    reportCard.setPj("https://sira-bucket.s3.us-east-1.amazonaws.com/documents/eleves/22053/Bulletin_22053_2023-2024_S2.pdf?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIA5FTY6ZLJ2XEJMRSV%2F20240805%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240805T102640Z&X-Amz-Expires=522800&X-Amz-SignedHeaders=host&X-Amz-Signature=43717f04ff6e44f6f345999414ed0632b6992f2aa4aa3b56e0633e77e8611ac8");
                    reportCard.setNom(rs.getString("pj"));
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