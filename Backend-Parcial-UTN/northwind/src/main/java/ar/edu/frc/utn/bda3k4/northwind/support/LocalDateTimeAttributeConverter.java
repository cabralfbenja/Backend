package ar.edu.frc.utn.bda3k4.northwind.support;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDate, String> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String convertToDatabaseColumn(LocalDate locDate) {
        return (locDate == null ? null : locDate.format(formatter));
    }

    @Override
    public LocalDate convertToEntityAttribute(String sqlDate) {
        return (sqlDate == null ? null : LocalDate.parse(sqlDate, formatter));
    }
}