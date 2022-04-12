package mk.ukim.finki.library.Service.Impl;

import mk.ukim.finki.library.Model.Country;
import mk.ukim.finki.library.Model.Exceptions.CountryNotFoundException;
import mk.ukim.finki.library.Repository.CountryRepository;
import mk.ukim.finki.library.Service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public Country create(Country country) {
        return countryRepository.save(country);
    }
}
