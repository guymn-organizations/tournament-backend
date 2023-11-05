package tour.rov.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tour.rov.profile.model.payment;
import tour.rov.profile.service.PaymentService;

@RestController
@RequestMapping("payment")
@CrossOrigin(origins = { "http://localhost:4200/" })
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable String id){
        try {
            payment payment = paymentService.findById(id);
            
            if (payment == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found with ID: " + id);
            }

            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve the payment: " + e.getMessage());
        }
    }
}
