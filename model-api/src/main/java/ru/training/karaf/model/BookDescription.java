package ru.training.karaf.model;

import java.util.Collection;

public interface BookDescription {

    long getId();
    String getDescription();
    String getDate();
    boolean isNull();
}
