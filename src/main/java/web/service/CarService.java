package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.List;

@Service
public class CarService {
    private final List<Car> cars = List.of(new Car("Ford", "Mustang"),
            new Car("McLaren", "F1"),
            new Car("Chevrolet", "Camaro"),
            new Car("Dodge", "Dodge Challenger"),
            new Car("Lada", "Vesta"));

    public List<Car> getCars(int count) {
        if (count >= cars.size()) {
            return cars;
        }
        return cars.subList(0, count);
    }
}
