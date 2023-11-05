package tour.rov.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tour.rov.profile.model.payment;

public interface PaymentRepo extends MongoRepository<payment, String>{
    
}
