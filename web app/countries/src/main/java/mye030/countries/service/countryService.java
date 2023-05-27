package mye030.countries.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mye030.countries.model.Country;

@Service
public interface countryService {
    List<Country> getAllCountries();
}
