package pl.csv.api.controller;

import com.sun.istack.internal.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.csv.api.Model.Car;
import pl.csv.api.Repository.CarRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/car/api")
@RequiredArgsConstructor
public class Controller {

    @NotNull
    private final CarRepository carRepository;

    @GetMapping
    public List<Car> readAllPictures() {
        return carRepository.getUsers();
    }

}
