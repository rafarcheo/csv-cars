package pl.csv.Model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvCar {

    @CsvBindByName(column = "Id")
    private long id;
    @CsvBindByName(column = "Nazwa")
    private String nazwa;
    @CsvBindByName(column = "Data zakupu")
    private String data_zakupu;
    @CsvBindByName(column = "Kolor")
    private String  kolor;
}
