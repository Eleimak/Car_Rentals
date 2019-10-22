package tam.group_bbv181.car_rental.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tam.group_bbv181.car_rental.model.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
}
