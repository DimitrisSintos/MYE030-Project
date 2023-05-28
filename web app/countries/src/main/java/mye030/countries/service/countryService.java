package mye030.countries.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import mye030.countries.dto.ResponseDTO;
import mye030.countries.dto.FieldsRequestDTO;
import mye030.countries.dto.RequestDTO;
import mye030.countries.model.Country;

@Service
public interface countryService {
    List<Country> getAllCountries();

    ResponseDTO processRequest(RequestDTO request);

}
