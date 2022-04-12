package mk.ukim.finki.library.Service.Impl;

import mk.ukim.finki.library.Model.Author;
import mk.ukim.finki.library.Model.Exceptions.AuthorNotFoundException;
import mk.ukim.finki.library.Repository.AuthorRepository;
import mk.ukim.finki.library.Service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findWithId(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }
}
