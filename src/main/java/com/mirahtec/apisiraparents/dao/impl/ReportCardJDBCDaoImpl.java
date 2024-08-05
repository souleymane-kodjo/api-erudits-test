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
                    reportCard.setPj("https://sira-bucket.s3.amazonaws.com/documents/eleves/22053/Calendrier_Paiements_22053_Janvier.pdf?AWSAccessKeyId=ASIA5FTY6ZLJS2JXA2Y7&Signature=s7Ms2O2mRXjRHS9NPd%2FdUoCPF7c%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEOr%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJIMEYCIQC11cyMWj2%2Fb%2FbC4o5eBOYbvOZrh7qjPbhamZEYm%2BiSVAIhAPIl02uvSo1e%2FjqPP1bcFA1Evx3RgCRxKIZPhY3CmVhEKvsCCNL%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEQABoMOTA1NDE4MDMzODc1Igz45RdQ94RPRrx%2BxFIqzwKUjhlPsns5Z7d23mE3eh84xqyQNLtGS%2Bp3ZLf189PiQ3i9%2FKvpitquWphaIu%2Flq9VSr4wLF0G4uu3%2FEcYqQEHzsZJOPklJp1IYWJRgu8KZCrpqWPjddWsM3RYxmDdXeDjQHWNbu65%2BpBBw3vDhOvfE3L4AhPMUhSVJZ0AlYKEm%2Bn%2BDHTHTE2%2FDqzbmJc%2BpXPguzbmLfWlS1UZmjT97eUnoJWRI1IFcVlWmGqWFSot30x51zRzCIWSDvECE0ou97PE8oUkO13s17y26ElYppu8nxoq1vkwnmF7fuXLw5zQArWEtxg6To2XdrSePV0zChZoNbKQV1nyGNp0X1ghiUB3zRvtpiPLLxo0p7eLocrYgS2mm6vYNDzgTxHfJDxnGr7%2Fk0I2yP%2FZFaI1CW2SDVQZDn43vKuerLecEx%2B1pvdGdIz5nOa9uKfGNDE1UGHF34TC5rMK1BjqdAW1kIEaUBFdu7oDQD7Y2ELKA2hHt71a%2B9RIgt4MGrz4AAcQNhbxCBbpy8G9N7AfwfPdCaaAsE2Z3FeawXSy2%2FbscjpwekdKHPufUuHI22%2BWdfhspNNbhvKc2uvUdCtTvL%2Fpr1SFWy4dAcm7p0inRpziOtGwykNWuBfQfaInr88psO8DbQt4Ib%2BMlVWsWFsQ%2FRTvqtqUFhobSadbd7Qs%3D&Expires=1722852741");
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