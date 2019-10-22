package tam.group_bbv181.car_rental.services.rentcar.interfaces;

import tam.group_bbv181.car_rental.model.RentCar;

import java.util.List;

public interface IRentCarService {
    List<RentCar> getAll();
    RentCar get(String id);
    RentCar create(RentCar rentCar);
    RentCar update(RentCar rentCar);
    RentCar delete(String id);
}
