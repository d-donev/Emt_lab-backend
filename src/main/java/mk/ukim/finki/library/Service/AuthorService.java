package mk.ukim.finki.library.Service;

import mk.ukim.finki.library.Model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findWithId(Long id);

    Author create(Author author);
}
