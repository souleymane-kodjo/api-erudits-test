package com.mirahtec.apisiraparents.service.payment;

import com.mirahtec.apisiraparents.dao.payment.PaymentJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PaymentService implements IPaymentService{
    @Autowired
    PaymentJDBCDaoImpl paymentJDBCDao;
    @Override
    public List<Payment> getPaymentsMonthByStudentMatricule(String matricule) {
        return paymentJDBCDao.getPaymentsMonthByStudentMatricule(matricule);
    }
    @Override
    public HashMap<String, String> getCumulePaymentsByStudentMatricule(String matricule) {
        return paymentJDBCDao.getCumulePaymentsByStudentMatricule(matricule);
    }
}
