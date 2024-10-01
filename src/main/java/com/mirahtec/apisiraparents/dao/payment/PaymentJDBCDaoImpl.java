package com.mirahtec.apisiraparents.dao.impl;

import com.mirahtec.apisiraparents.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class PaymentJDBCDaoImpl {
    @Autowired
    JdbcTemplate beanJDBCTemplate;

    public List<Payment> getPaymentsMonthByStudentMatricule(String matricule) {
        String sql = "SELECT * FROM  m_paye_scolarite_mensuel WHERE matricule=? " ;
        List<Payment> payments = beanJDBCTemplate.query(sql, new Object[]{matricule}, (rs, rowNum) -> {
            Payment payment = new Payment();
            payment.setMatricule(rs.getString("matricule"));
            payment.setMois(rs.getString("mois"));
            payment.setJustificatif(rs.getString("justificatif"));
            payment.setReliquat(rs.getString("reliquat"));
            payment.setScolarite(rs.getString("scolarite"));
            payment.setTotal(rs.getDouble("total"));
            payment.setDate_paiement(rs.getString("date_paiement"));
            payment.setMode_paiement(rs.getString("mode_paiement"));
            return payment;
        });
        return payments;
    }

    public HashMap<String, String> getCumulePaymentsByStudentMatricule(String matricule) {
        String sql = "SELECT SUM(total) t_paye, SUM(reliquat) as t_dettes FROM `m_paye_scolarite_mensuel` where matricule= ?" ;
        return beanJDBCTemplate.queryForObject(sql, new Object[]{matricule}, (rs, rowNum) -> {
            HashMap<String, Double> map = new HashMap<>();
            double t_paye = rs.getDouble("t_paye");
            double t_dettes = rs.getDouble("t_dettes");
            HashMap<String, String> result = new HashMap<>();
            result.put("t_paye", String.valueOf(t_paye));
            result.put("t_dettes", String.valueOf(t_dettes));
            return result;
        });
    }
}
