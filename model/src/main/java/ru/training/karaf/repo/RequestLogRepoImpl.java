package ru.training.karaf.repo;

import org.apache.aries.jpa.template.JpaTemplate;
import ru.training.karaf.model.RequestLog;
import ru.training.karaf.model.RequestLogDO;

import java.util.List;
import java.util.stream.Collectors;

public class RequestLogRepoImpl implements RequestLogRepo {

    private JpaTemplate template;

    public RequestLogRepoImpl(JpaTemplate template) {
        this.template = template;
    }

    @Override
    public List<RequestLog> getAll() {
        return template.txExpr(em -> em.createNamedQuery(RequestLogDO.GET_ALL, RequestLogDO.class).getResultList())
                .stream()
                .map(RequestLogImpl::new)
                .collect(Collectors.toList());
    }

    @Override
    public void add(String name, String author, int price) {
        RequestLogDO requestLogToCreate = new RequestLogDO();

        requestLogToCreate.setName(name);
        requestLogToCreate.setAuthor(author);
        requestLogToCreate.setPrice(price);

        template.tx(em -> em.persist(requestLogToCreate));
    }
}
class RequestLogImpl implements RequestLog {

    private RequestLogDO requestLogDO;

    RequestLogImpl(RequestLogDO requestLogDO) {
        this.requestLogDO = requestLogDO;
    }
    @Override
    public String getName() {
        return requestLogDO.getName();
    }

    @Override
    public String getAuthor() {
        return requestLogDO.getAuthor();
    }

    @Override
    public int getPrice() {
        return requestLogDO.getPrice();
    }
}
