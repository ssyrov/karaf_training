package ru.training.karaf.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class DateConverter implements AttributeConverter<LocalDateTime, String> {

    private static final String FORMAT = "yyyy-MM-dd kk:mm:ss";

    @Override
    public String convertToDatabaseColumn(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern(FORMAT).format(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(FORMAT));

    }
}
