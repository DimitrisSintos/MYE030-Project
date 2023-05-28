package mye030.countries.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mye030.countries.dao.countryRepository;
import mye030.countries.dao.metricsRepository;
import mye030.countries.dto.FieldsRequestDTO;
import mye030.countries.dto.RequestDTO;
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
    public ResponseDTO processRequest(RequestDTO request) {
        // get the countries from the request
        List<String> countries = request.getCountries();

        // get the fields from the request
        Map<String, List<String>> fields = request.getFields();

        // get the min and max years from the request
        Integer minYear = Integer.valueOf(request.getYears().getMin());
        Integer maxYear = Integer.valueOf(request.getYears().getMax());

        // keep track of all the individual country-fields sets
        Map<String, Map<String, Object>> results = new HashMap<>();

        // go through every country in the request
        for (String countryIso: countries) {
            // initialize the results for the current country
            results.put(countryIso, new HashMap<>());

            // go through every field (metric) in the request
            for (String tableName: fields.keySet()) {

                // get all the field (metric) names for the specific table
                List<String> fieldNames = fields.get(tableName);

                // get the data for the current country and metric
                Map<String, Map<String, Object>> data = findDataForCountry(countryIso, fieldNames, tableName);


                // add the data for the current countryIsoCode to the results
                results.get(countryIso).put(tableName, data);
            }
        }

        
        ResponseDTO response = ResponseDTO.mapToResponseDTO(results, minYear, maxYear);
        return response;



    }
    
    private Map<String, Map<String, Object>> findDataForCountry(String isoCode, List<String> fieldNames, String tableName) {
        List<Map<String, Object>> resultRows = metricsRepository.findDataByCountryAndFields(isoCode, fieldNames, tableName);

        Map<String, Map<String, Object>> result = new HashMap<>();

        // for the case where the tableName is the same as the fieldName (usually economic metrics)
        if (fieldNames == null || fieldNames.isEmpty()) {
            String fieldName = tableName;
            result.put(fieldName, resultRows.get(0));
        }

        else {
            for (int i = 0; i < resultRows.size(); i++) {
                String fieldName = fieldNames.get(i);
                result.put(fieldName, resultRows.get(i));
            }
        }

        return result;
    }
}
