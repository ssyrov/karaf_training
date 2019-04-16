package ru.training.karaf.converter;

import ru.training.karaf.model.ColorImpl;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ColorConverter implements AttributeConverter<ColorImpl, String> {
    private static final String SEPARATOR = ", ";
    @Override
    public String convertToDatabaseColumn(ColorImpl color) {
        return color.getRed() + SEPARATOR +
                color.getGreen() + SEPARATOR +
                color.getBlue() + SEPARATOR +
                color.getAlpha();
    }

    @Override
    public ColorImpl convertToEntityAttribute(String s) {
        String[] split = s.split(SEPARATOR);
        return new ColorImpl(
                Integer.parseInt(split[0]),
                Integer.parseInt(split[1]),
                Integer.parseInt(split[2]),
                Integer.parseInt(split[3])
        );
    }
}
