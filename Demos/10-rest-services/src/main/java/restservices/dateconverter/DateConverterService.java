package restservices.dateconverter;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateConverterService {

    public String convert(String date) {
        final String[] splitDate = date.split("\\.");
        int day = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);

        LocalDate localDate = LocalDate.of(year, month, day);
        return localDate.getDayOfWeek().toString();
    }

}
