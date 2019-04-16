package ru.training.karaf.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class DateConverter implements AttributeConverter<LocalDateTime, String> {
    @Override
    public String convertToDatabaseColumn(LocalDateTime localDateTime) {
        String format = String.format("%d-%d-%d %d:%d:%d",
                localDateTime.getYear(),
                localDateTime.getMonthValue(),
                localDateTime.getDayOfMonth(),
                localDateTime.getHour(),
                localDateTime.getMinute(),
                localDateTime.getSecond()
        );
        return format;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String s) {
        String[] s1 = s.split(" ");
        String[] dateS = s1[0].split("-");
        String[] timeS = s1[1].split(":");
        List<Integer> date = Arrays.stream(dateS).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> time = Arrays.stream(timeS).map(Integer::parseInt).collect(Collectors.toList());
        LocalDate localDate = LocalDate.of(date.get(0), date.get(1), date.get(2));
        LocalTime localTime = LocalTime.of(time.get(0), time.get(1), time.get(2));

        return LocalDateTime.of(localDate, localTime);
    }
}
