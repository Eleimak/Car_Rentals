package tam.group_bbv181.car_rentals.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tam.group_bbv181.car_rentals.model.ReturnRentCar;

@Repository
public interface ReturnRentCarRepository extends MongoRepository<ReturnRentCar, String> {
}
