package pl.csv.api.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.csv.api.DataLoader.CsvDataLoader;
import pl.csv.api.Model.Car;

import java.util.List;

@Component
public class CarRepository {

    private static String CARS_FILE = "cars.csv";

    @Autowired
    private CsvDataLoader csvDataLoader;

    public List<Car> getUsers() {

        List<Car> users = csvDataLoader.loadObjectList(Car.class, CARS_FILE);
        return users;
    }

    private Car findByUserByName(List<Car> users, String name) {
        return users.stream().
                filter(item -> item.getNazwa().equals(name)).findFirst().get();
    }
}
