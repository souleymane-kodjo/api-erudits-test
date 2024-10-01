package com.mirahtec.apisiraparents.service.payment;

import com.mirahtec.apisiraparents.model.Payment;

import java.util.HashMap;
import java.util.List;

public interface IPaymentService {
    List<Payment> getPaymentsMonthByStudentMatricule(String matricule);

    HashMap<String, String> getCumulePaymentsByStudentMatricule(String matricule);
}
