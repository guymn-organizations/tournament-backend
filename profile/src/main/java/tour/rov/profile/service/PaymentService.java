package tour.rov.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tour.rov.profile.model.payment;
import tour.rov.profile.repository.PaymentRepo;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;

    public payment findById(String id) {
        return paymentRepo.findById(id).get();
    }

}
