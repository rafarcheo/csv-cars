package pl.csv.Repository;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.csv.Model.CsvCar;
import pl.csv.Reader.OpenCSVReader;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class CarRepository {

    public List<CsvCar> getAll() throws IOException {
        return OpenCSVReader.getAll();
    }

    public CsvCar findById(Long requestedId) throws IOException {
        return OpenCSVReader.getById(requestedId);
    }

    public CsvCar addNew(CsvCar newCsvCar) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
        return OpenCSVReader.addRow(newCsvCar);
    }

    public void deleteById(long id) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        OpenCSVReader.deleteById(id);
    }

    public CsvCar updateById(CsvCar updateCsvCar) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        return OpenCSVReader.updateById(updateCsvCar);
    }
}
