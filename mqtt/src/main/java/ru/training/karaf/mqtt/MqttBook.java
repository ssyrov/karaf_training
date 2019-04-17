package ru.training.karaf.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;
import ru.training.karaf.model.Book;
import ru.training.karaf.repo.BookPriceRepo;
import ru.training.karaf.repo.BookRepo;
import ru.training.karaf.repo.RequestLogRepo;

import java.util.Optional;

public class MqttBook {

    private static final String URI = "tcp://localhost:1883";
    private static final String TOPIC = "book_price";
    private BookRepo bookRepo;
    private BookPriceRepo bookPriceRepo;
    private RequestLogRepo requestLogRepo;

    private static final boolean LOG = true;

    public MqttBook() {
        try {
            MqttClient client = new MqttClient(URI, MqttClient.generateClientId());
            client.setCallback(new BookPriceCallBack());
            client.connect();

            client.subscribe(TOPIC);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void setBookRepo(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void setBookPriceRepo(BookPriceRepo bookPriceRepo) {
        this.bookPriceRepo = bookPriceRepo;
    }

    public void setRequestLogRepo(RequestLogRepo requestLogRepo) {
        this.requestLogRepo = requestLogRepo;
    }

    class BookPriceCallBack implements MqttCallback {

        @Override
        public void connectionLost(Throwable throwable) {
            System.out.println("Connection to MQTT broker lost!");
        }

        @Override
        public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
            if (s == null) {
                return;
            }
            Message message = new ObjectMapper().readValue(mqttMessage.getPayload(), Message.class);

            Optional<Book> byNameAndAuthor = bookRepo.getByNameAndAuthor(message.getName(), message.getAuthor());

            if (byNameAndAuthor.isPresent()) {
                //bookRepo.changePrice(message.getName(), message.getAuthor(), message.getPrice());
                bookPriceRepo.create(message.getPrice(), byNameAndAuthor.get());
            } else {
                requestLogRepo.add(message.getName(), message.getAuthor(), message.getPrice());
            }

        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

        }
    }

}
