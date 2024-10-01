package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.payment.PaymentJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentJDBCDaoImpl paymentJDBCDao;

    public List<Payment> getPaymentsMonthByStudentMatricule(String matricule) {
        return paymentJDBCDao.getPaymentsMonthByStudentMatricule(matricule);
    }
    public HashMap<String, String> getCumulePaymentsByStudentMatricule(String matricule) {
        return paymentJDBCDao.getCumulePaymentsByStudentMatricule(matricule);
    }
//    public double getCumulePaymentsByStudentMatricule(String matricule) {
//        List<Payment> payments = getPaymentsMonthByStudentMatricule(matricule);
//        double cumulePayments = 0;
//        for (Payment payment : payments) {
//            cumulePayments += Double.parseDouble(payment.getScolarite());
//        }
//        return cumulePayments;
//    }
}
