package mk.ukim.finki.library.Service.Impl;

import mk.ukim.finki.library.Model.Author;
import mk.ukim.finki.library.Model.Book;
import mk.ukim.finki.library.Model.DTO.BookDTO;
import mk.ukim.finki.library.Model.Enumerations.Category;
import mk.ukim.finki.library.Model.Exceptions.AuthorNotFoundException;
import mk.ukim.finki.library.Model.Exceptions.BookNotFoundException;
import mk.ukim.finki.library.Repository.AuthorRepository;
import mk.ukim.finki.library.Repository.BookRepository;
import mk.ukim.finki.library.Service.BookService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    public Book create(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = new Book(name,category,author,availableCopies);
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> saveFromDto(BookDTO bookDTO) {
        Author author = authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException(bookDTO.getAuthorId()));
        return Optional.of(bookRepository.save(new Book(bookDTO.getName(),bookDTO.getCategory(),author,bookDTO.getAvailableCopies())));
    }

    @Override
    public Optional<Book> edit(Long id, BookDTO bookDTO) {
        Author author = authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException(bookDTO.getAuthorId()));
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setName(bookDTO.getName());
        book.setAuthor(author);
        book.setCategory(bookDTO.getCategory());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.delete(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setAvailableCopies(book.getAvailableCopies()-1);
        return Optional.of(bookRepository.save(book));
    }
}
