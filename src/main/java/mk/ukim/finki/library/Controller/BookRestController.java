package mk.ukim.finki.library.Controller;

import mk.ukim.finki.library.Model.Book;
import mk.ukim.finki.library.Model.DTO.BookDTO;
import mk.ukim.finki.library.Model.Enumerations.Category;
import mk.ukim.finki.library.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class BookRestController {

    private final BookService bookService;


    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping ("/add")
    public ResponseEntity<Book> createBook(@RequestBody BookDTO bookDTO) {
        return bookService.saveFromDto(bookDTO)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @PutMapping ("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id,@RequestBody BookDTO bookDTO) {
        return bookService.edit(id,bookDTO)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        return this.bookService.delete(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/mark/{id}")
    public ResponseEntity<Book> markAsTakenBook(@PathVariable Long id) {
        return bookService.markAsTaken(id)
                .map(book -> ResponseEntity.ok().body(book)) //proveri dali kje raboti samo so response.ok.build
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/categories")
    public List<Category> findAllCategories() {
        List<Category> categories = Arrays.asList(Category.values());
        return categories;
    }


}
