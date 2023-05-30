package mye030.countries.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;

public class TimeseriesResponseDTO {
    private Map<String, Map<String, FieldDTO>> data;


    // mapper method to turn a List<Map<String, Object>> into a ResponseDTO
    public static TimeseriesResponseDTO mapToResponseDTO(Map<String, Map<String, Object>> results, int minYear, int maxYear) {
        if (results.isEmpty()) {
            // Handle empty result case if needed
            return null;
        }

        TimeseriesResponseDTO responseDTO = new TimeseriesResponseDTO();
        Map<String, Map<String, FieldDTO>> data = new HashMap<>();


        // go through each entry of the results map
        for (Map.Entry<String, Map<String, Object>> country : results.entrySet()) {
            // extract the metric name and the corresponding results
            String isoCode = country.getKey();
            Map<String, Object> countryData = country.getValue();

            // add new entry for the current iso_code
            if (!data.containsKey(isoCode)) {
                data.put(isoCode, new HashMap<>());
            }

            
            for (Map.Entry<String, Object> entry : countryData.entrySet()) {
                Object metrics = entry.getValue();

                
                Map<String, Object> metricsMap = (Map<String, Object>) metrics;
                for (Map.Entry<String, Object> metric : metricsMap.entrySet()) {
                    String metricName = metric.getKey();
                    Object row = metric.getValue();
                    
                    FieldDTO fieldDTO = new FieldDTO();
                    Map<String, Object> rowMap = (Map<String, Object>) row;
                    for (Map.Entry<String, Object> rowEntry : rowMap.entrySet()) {
                        String year = rowEntry.getKey();
                        Object value = rowEntry.getValue();

                        List<String> xValues = fieldDTO.getX();
                        List<String> yValues = fieldDTO.getY();

                        if (xValues == null || yValues == null) {
                            xValues = new ArrayList<>();
                            yValues = new ArrayList<>();
                        }

                        if (year.matches("\\d{4}") && isInRange(year, minYear, maxYear)) {
                            xValues.add(year);

                            if (value == null) {
                                yValues.add("null");
                            } 
                            // if data for a year is not available, the value is null
                            else {
                                yValues.add(value.toString());
                            }
                        }

                        
                        fieldDTO.setX(xValues);
                        fieldDTO.setY(yValues);
                    }


                    data.get(isoCode).put(metricName, fieldDTO);
                }
            }
        }

        responseDTO.setData(data);
        return responseDTO;
    }

    private static boolean isInRange(String year, int start, int end) {
        int yearInt = Integer.parseInt(year);
        return yearInt >= start && yearInt <= end;
    }


    public Map<String, Map<String, FieldDTO>> getData() {
        return data;
    }


    public void setData(Map<String, Map<String, FieldDTO>> data) {
        this.data = data;
    }




}
