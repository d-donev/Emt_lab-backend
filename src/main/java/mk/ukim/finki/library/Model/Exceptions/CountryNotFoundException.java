package mk.ukim.finki.library.Model.Exceptions;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(Long id) {
        super(String.format("Country with id: %d not found!",id));
    }
}
