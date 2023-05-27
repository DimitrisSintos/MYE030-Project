package mye030.countries.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mye030.countries.dao.countryRepository;
import mye030.countries.dao.metricsRepository;
import mye030.countries.dto.ResponseDTO;
import mye030.countries.model.Country;

@Service
public class countryServiceImpl implements countryService {
    @Autowired
    private countryRepository countryRepository;

    @Autowired
    private metricsRepository metricsRepository;



    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();

    }


    @Override
    public ResponseDTO findGdpPerCapitaByCountries(List<String> isoCodes) {
        List<Map<String, Object>> result = metricsRepository.findGdpPerCapitaByCountries(isoCodes);
        // System.out.println(result);
        ResponseDTO response = ResponseDTO.mapToResponseDTO(result);
        System.out.println("response" + response);
        return response;
    }
    
}
