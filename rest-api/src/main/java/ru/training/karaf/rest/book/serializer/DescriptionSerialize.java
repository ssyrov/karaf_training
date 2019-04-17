package ru.training.karaf.rest.book.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.training.karaf.model.BookDescription;

import java.io.IOException;

public class DescriptionSerialize extends JsonSerializer<BookDescription> {
    @Override
    public void serialize(BookDescription bookDescription, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (bookDescription.isNull()) {
            jsonGenerator.writeString("absent");
        } else {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("text", bookDescription.getDescription());
            jsonGenerator.writeStringField("date", bookDescription.getDate());
            jsonGenerator.writeEndObject();
        }
    }
}
