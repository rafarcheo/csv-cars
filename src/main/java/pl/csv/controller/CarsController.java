package pl.csv.controller;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sun.istack.internal.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.csv.Model.CsvCar;
import pl.csv.Repository.CarRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/car/api")
@RequiredArgsConstructor
@Slf4j
public class CarsController {

    @NotNull
    private final CarRepository carRepository;

    @GetMapping
    public List<CsvCar> readAll() throws IOException {
        return carRepository.getAll();
    }

    @GetMapping(value ="/{id}")
    public CsvCar getById(@PathVariable Long id)
            throws IOException {
        return carRepository.findById(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CsvCar addNew(@RequestBody CsvCar newCar)
            throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
        return carRepository.addNew(newCar);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById (@PathVariable Long id)
            throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        carRepository.deleteById(id);
    }

    @PatchMapping
    public CsvCar updateItem(@RequestBody CsvCar updateCar)
            throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        return carRepository.updateById(updateCar);
    }
}
