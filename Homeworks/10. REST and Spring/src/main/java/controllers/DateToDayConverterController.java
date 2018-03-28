package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.DateToDayConverterService;

@RestController
@RequestMapping("/datetoday")
public class DateToDayConverterController {

    private final DateToDayConverterService dateToDayConverterService;

    @Autowired
    public DateToDayConverterController(DateToDayConverterService dateToDayConverterService) {
        this.dateToDayConverterService = dateToDayConverterService;
    }

    @RequestMapping("")
    public String convertDateToDay(@RequestParam(value = "date") String date) {
        return this.dateToDayConverterService.convertDateToDay(date);
    }
}
