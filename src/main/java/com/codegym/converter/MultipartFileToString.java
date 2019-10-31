//package com.codegym.converter;
//
//import org.springframework.core.convert.converter.Converter;
//
//import java.io.File;
//import java.nio.file.FileAlreadyExistsException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//
//public class MultipartFileToString implements Converter<File, String> {
//    private File datePattern;
//
//    public MultipartFileToString(File datePattern) {
//        this.datePattern = datePattern;
//    }
//
//    @Override
//    public String convert(File source) {
//        try{
//            return source.toString();
//        }catch (DateTimeParseException e) {
//            // the error message will be displayed in <form:errors>
//            throw new IllegalArgumentException("invalid date format. Please use this pattern\""
//                    + datePattern + "\"");
//    }
//
////    @Override
////    public LocalDate convert(String s) {
////        try {
////            return LocalDate.parse(s, DateTimeFormatter.ofPattern(datePattern));
////        } catch (DateTimeParseException e) {
////            // the error message will be displayed in <form:errors>
////            throw new IllegalArgumentException("invalid date format. Please use this pattern\""
////                    + datePattern + "\"");
////        }
////    }
//}
//}