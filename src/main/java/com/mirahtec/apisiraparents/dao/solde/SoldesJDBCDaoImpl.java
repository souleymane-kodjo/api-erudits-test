package com.mirahtec.apisiraparents.dao.solde;

import com.mirahtec.apisiraparents.model.Payment;
import com.mirahtec.apisiraparents.model.Solde;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class SoldesJDBCDaoImpl  implements ISoldeDao {
    @Autowired
    private JdbcTemplate beanJDBCTemplate;

    public List<Solde> getSoldesByStudentMatricule(String matricule) {
        String sql = "SELECT * FROM m_a_payer_scolarite_mensuel WHERE matricule=? " ;
        List<Solde> payments = beanJDBCTemplate.query(sql, new Object[]{matricule}, (rs, rowNum) -> {
            Solde payment = new Solde();
            payment.setId(rs.getInt("id"));
            payment.setMatricule(rs.getString("matricule"));
            payment.setMois(rs.getString("mois"));
            payment.setReliquat(rs.getString("reliquat"));
            payment.setScolarite(rs.getString("scolarite"));
            payment.setTotal(rs.getDouble("total"));
            return payment;
        });
        return payments;
    }
}
