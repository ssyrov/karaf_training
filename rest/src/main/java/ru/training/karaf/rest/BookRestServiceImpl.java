package ru.training.karaf.rest;

import ru.training.karaf.repo.BookDescriptionRepo;
import ru.training.karaf.repo.BookRepo;
import ru.training.karaf.repo.UserRepo;
import ru.training.karaf.rest.book.BookRestService;
import ru.training.karaf.rest.book.dto.BookDTO;
import ru.training.karaf.rest.book.dto.BookDescriptionDTO;
import ru.training.karaf.rest.book.dto.BookPriceDTO;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

public class BookRestServiceImpl implements BookRestService {

    private BookRepo bookRepo;
    private UserRepo userRepo;
    private BookDescriptionRepo descriptionRepo;

    public void setBookRepo(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public void setBookDescriptionRepo(BookDescriptionRepo descriptionRepo) {
        this.descriptionRepo = descriptionRepo;
    }

    @Override
    public List<BookDTO> getAll() {
        return bookRepo.getAll().stream().map(BookDTO::new).collect(Collectors.toList());
    }

    @Override
    public void create(BookDTO book) {
        bookRepo.create(book.getName(), book.getUsr(), book.getPrice(), book.getColor().getRed(), book.getColor().getGreen(), book.getColor().getBlue(), book.getColor().getAlpha(), book.getAuthors());

        userRepo.updateCount(book.getUsr());
    }

    @Override
    public BookDTO get(long id) {
        return bookRepo.get(id).map(BookDTO::new)
                .orElseThrow(() -> new NotFoundException(Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.TEXT_HTML).entity("Book not found").build()));
    }

    @Override
    public void delete(String name) {
        bookRepo.delete(name);
    }

    @Override
    public List<BookDTO> getByUsr(String usr) {
        return bookRepo.getByUsr(usr).stream().map(BookDTO::new).collect(Collectors.toList());
    }

    @Override
    public void addDescription(long idBook, BookDescriptionDTO description) {
        descriptionRepo.create(idBook, description.getDescription(), description.getDate());
    }

    @Override
    public List<BookPriceDTO> getPrices(long bookId) {
        return bookRepo.getPrices(bookId).stream().map(BookPriceDTO::new).collect(Collectors.toList());
    }

}
