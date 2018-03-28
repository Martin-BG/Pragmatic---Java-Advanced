package services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DateToDayConverterService {

    private final static DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d.M.y");

    public String convertDateToDay(String dateString) {

        LocalDate date = LocalDate.parse(dateString, INPUT_DATE_FORMAT);

        return date.getDayOfWeek().toString();
    }
}
