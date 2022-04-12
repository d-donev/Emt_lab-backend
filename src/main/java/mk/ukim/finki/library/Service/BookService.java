package mk.ukim.finki.library.Service;

import mk.ukim.finki.library.Model.Author;
import mk.ukim.finki.library.Model.Book;
import mk.ukim.finki.library.Model.DTO.BookDTO;
import mk.ukim.finki.library.Model.Enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book create (String name, Category category, Long authorId, Integer availableCopies);

    Optional<Book> saveFromDto (BookDTO bookDTO);

    Optional<Book> edit (Long id, BookDTO bookDTO);

    Optional<Book> delete(Long id);

    Optional<Book> markAsTaken(Long id);

}
