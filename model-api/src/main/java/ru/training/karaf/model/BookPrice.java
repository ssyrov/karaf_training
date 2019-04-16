package ru.training.karaf.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface BookPrice {
    long getId();
    int getPrice();
    LocalDateTime getDate();
    Book getBook();
}
