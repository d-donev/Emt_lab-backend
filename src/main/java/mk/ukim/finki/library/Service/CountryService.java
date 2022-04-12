package mk.ukim.finki.library.Service;

import mk.ukim.finki.library.Model.Country;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface CountryService {

    List<Country> findAll();

    Country findById(Long id);

    Country create (Country country);

}
