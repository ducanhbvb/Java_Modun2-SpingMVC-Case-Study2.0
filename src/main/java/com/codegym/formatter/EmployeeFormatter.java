//package com.codegym.formatter;
//
//import org.springframework.format.Formatter;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Locale;
//
//@Component
//public class EmployeeFormatter implements Formatter<LocalDate> {
//
//    @Override
//    public LocalDate parse(String date, Locale locale) throws ParseException {
//        return LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
//    }
//
//    @Override
//    public String print(LocalDate object, Locale locale) {
//        return object.toString();
//    }
//}