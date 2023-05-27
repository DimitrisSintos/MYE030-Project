package mye030.countries.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mye030.countries.dao.countryRepository;
import mye030.countries.model.Country;

@Service
public class countryServiceImpl implements countryService {
    @Autowired
    private countryRepository countryRepository;


    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();

    }
    
}
