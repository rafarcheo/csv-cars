package pl.csv.Reader;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.csv.Model.CsvCar;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
public class OpenCSVReader {

    private static final String SAMPLE_CSV_FILE_PATH = "src/main/resources/cars.csv";

    public static List<CsvCar> getAll()
        throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));

        CsvToBean<CsvCar> csvToBean = new CsvToBeanBuilder(reader)
                .withType(CsvCar.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        return csvToBean.parse();
    }

    public static CsvCar getById(long requestedId)
        throws IOException {
        return getAll().stream()
            .filter(item -> Long.valueOf(item.getId()).equals(requestedId)).findFirst().get();
    }

//    public static CsvCar addRow(CsvCar newCsvCar)
//        throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
//        List<CsvCar> csvCars = getAll();
//        long newId = (long) csvCars.size() + 1;
//        newCsvCar.setId(newId);
//        csvCars.add(newCsvCar);
//
//        updateCsv(csvCars);
//
//        return getById(newId);
//    }

    public static CsvCar addRow(CsvCar newCsvCar) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        List<CsvCar> csvCars = getAll();
        long newId = (long) csvCars.size() + 1;
        newCsvCar.setId(newId);
        csvCars.add(newCsvCar);

        updateCsv(csvCars);
        return getById(newId);
    }

    public static void deleteById(long requestedId)
        throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        final ArrayList<CsvCar> collect = getAll().stream().filter(item -> !Long.valueOf(item.getId()).equals(requestedId)).collect(Collectors.toCollection(ArrayList::new));

        updateCsv(collect);
    }

    public static CsvCar updateById(CsvCar updateCsvCar)
            throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        final List<CsvCar> csvCars = OpenCSVReader.getAll().stream()
                .map(carItem -> (Long.valueOf(updateCsvCar.getId()).equals(carItem.getId())) ? updateCsvCar : carItem)
                .collect(Collectors.toList());

        updateCsv(csvCars);

        return getById(updateCsvCar.getId());
    }


    private static void updateCsv(List<CsvCar> csvCars)
            throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH), StandardCharsets.UTF_8);
        ) {
            StatefulBeanToCsv<CsvCar> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(csvCars);


        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException exception) {
            throw exception;
        }
    }

}
