package mk.ukim.finki.library.Model.Exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id) {
        super(String.format("Book with id: %d not found!",id));
    }
}
