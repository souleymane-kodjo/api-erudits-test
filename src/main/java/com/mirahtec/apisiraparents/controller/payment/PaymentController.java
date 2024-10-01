package com.mirahtec.apisiraparents.controller.payment;

import com.mirahtec.apisiraparents.dto.PaymentResponse;
import com.mirahtec.apisiraparents.service.payment.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/payments")
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paiementService;
    //getPaymentsByStudentMatricule
    @RequestMapping("/month/student/{matricule}")
    public ResponseEntity<?> getPaymentsMonthByStudentMatricule(@PathVariable String matricule) {
        if (matricule == null || matricule.isEmpty()) {
            return ResponseEntity.badRequest().body("Matricule cannot be empty");
        }
        log.info("Get Payments by student matricule: {}", matricule);
        HashMap<String, String> cumulePayments = paiementService.getCumulePaymentsByStudentMatricule(matricule);
        PaymentResponse responsePayments = new PaymentResponse();
        responsePayments.setCumule_paiements(Double.parseDouble(cumulePayments.get("t_paye")));
        responsePayments.setImpayes(Double.parseDouble(cumulePayments.get("t_dettes")));
        responsePayments.setPayments(paiementService.getPaymentsMonthByStudentMatricule(matricule));
        ResponseEntity<?> response = new ResponseEntity<>(responsePayments, HttpStatus.OK);
        return response;
    }
}
