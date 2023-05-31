package mye030.countries.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mye030.countries.dao.countryRepository;
import mye030.countries.dao.metricsRepository;
import mye030.countries.dto.TimeseriesRequestDTO;
import mye030.countries.dto.TimeseriesResponseDTO;
import mye030.countries.dto.ScatterRequestDTO;
import mye030.countries.dto.ScatterResponseDTO;
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
    public TimeseriesResponseDTO processTimeseriesRequest(TimeseriesRequestDTO request) {
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
        
        TimeseriesResponseDTO response = TimeseriesResponseDTO.mapToResponseDTO(results, minYear, maxYear);
        return response;
    }

    @Override
    public ScatterResponseDTO processScatterRequest(ScatterRequestDTO request) throws IllegalArgumentException {
        // get the fixed field from the request
        String fixedField = request.getFixedField();

        // depending on what the fixed field is, call the appropriate method
        if (fixedField.equals("country")) {
            return fixedCountryScatter(request);
        }
        else if (fixedField.equals("year")) {
            return fixedYearScatter(request);
        }
        else {
            throw new IllegalArgumentException("Fixed field must be either 'country' or 'year'");
            // return null;
        }

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

    private List<Map<String, Object>> findDataForYear(String year, String fieldName, String tableName, List<String> countries) {
        List<Map<String, Object>> resultRows = metricsRepository.findDataByYearAndField(year, fieldName, tableName, countries);

        return resultRows;
    }


    private ScatterResponseDTO fixedCountryScatter(ScatterRequestDTO request) throws IllegalArgumentException {
        // get the country from the request
        String countryIso = request.getCountries().get(0);

        // get the fields from the request
        Map<String, String> xField = request.getxField();
        Map<String, String> yField = request.getyField();

        
        if (xField.isEmpty() || yField.isEmpty()) {
            throw new IllegalArgumentException("Both xField and yField must be present");
        }

        // get the table names from the request
        String xTable = xField.keySet().iterator().next();
        String yTable = yField.keySet().iterator().next();
        // get the field names from the request
        String xMetric = xField.values().iterator().next();
        List<String> xMetricList = Arrays.asList(xMetric);

        String yMetric = yField.values().iterator().next();
        List<String> yMetricList = Arrays.asList(yMetric);

        // get the min and max years from the request
        Integer minYear = Integer.valueOf(request.getYears().getMin());
        Integer maxYear = Integer.valueOf(request.getYears().getMax());

        
        Map<String, Map<String, Object>> result1;
        Map<String, Map<String, Object>> result2;
        Map<String, Object> data1 = null;
        Map<String, Object> data2 = null;

        result1 = findDataForCountry(countryIso, xMetricList, xTable);
        result2 = findDataForCountry(countryIso, yMetricList, yTable);
        try {
            data1 = result1.values().iterator().next();
            data2 = result2.values().iterator().next();
        }
        catch (Exception e) {
            System.out.println("No data found for country " + countryIso);
        }


        ScatterResponseDTO response = ScatterResponseDTO.mapToResponseDTO(data1, data2, minYear, maxYear);
        return response;
 
    }

    private ScatterResponseDTO fixedYearScatter(ScatterRequestDTO request) throws IllegalArgumentException {
        // get the fixed year from the request
        String year = request.getYears().getMin();

        // get the countries from the request
        List<String> countries = request.getCountries();

        // get the fields from the request
        Map<String, String> xField = request.getxField();
        Map<String, String> yField = request.getyField();

        
        if (xField.isEmpty() || yField.isEmpty()) {
            throw new IllegalArgumentException("Both xField and yField must be present");
        }

        // get the table names from the request
        String xTable = xField.keySet().iterator().next();
        String yTable = yField.keySet().iterator().next();
        // get the field names from the request
        String xMetric = xField.values().iterator().next();
        String yMetric = yField.values().iterator().next();

        
        List<Map<String, Object>> data1 = findDataForYear(year, xMetric, xTable, countries);
        List<Map<String, Object>> data2 = findDataForYear(year, yMetric, yTable, countries);

        ScatterResponseDTO response = ScatterResponseDTO.mapToResponseDTO(data1, data2, year);
        return response;
 
    }
}
