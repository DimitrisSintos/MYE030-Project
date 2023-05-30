package mye030.countries.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mye030.countries.dto.TimeseriesResponseDTO;
import mye030.countries.dto.TimeseriesRequestDTO;
import mye030.countries.dto.ScatterResponseDTO;
import mye030.countries.dto.ScatterRequestDTO;

import mye030.countries.model.Country;

@Service
public interface countryService {
    List<Country> getAllCountries();

    TimeseriesResponseDTO processTimeseriesRequest(TimeseriesRequestDTO request);

    ScatterResponseDTO processScatterRequest(ScatterRequestDTO request);

}
