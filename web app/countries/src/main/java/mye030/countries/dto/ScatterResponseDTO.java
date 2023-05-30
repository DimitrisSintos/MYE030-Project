package mye030.countries.dto;

import java.util.*;


import org.apache.catalina.connector.Response;

public class ScatterResponseDTO {
    private String country;
    private List<Map<String, Object>> dataPoints;

    // mapper method to turn a List<Map<String, Object>> into a ResponseDTO
    public static ScatterResponseDTO mapToResponseDTO(Map<String, Object> data1, Map<String, Object> data2, int minYear, int maxYear) {
        ScatterResponseDTO scatterResponseDTO = new ScatterResponseDTO();

        Map<String, Object> resultMap = new HashMap<>();

        // Extract iso_code from data1
        String isoCode = String.valueOf(data1.get("iso_code"));
        resultMap.put("iso_code", isoCode);

        // Extract the common years between data1 and data2
        Set<String> commonYears = new HashSet<>(data1.keySet());
        commonYears.retainAll(data2.keySet());

        // Create a list to hold the data points
        List<Map<String, Object>> dataPoints = new ArrayList<>();

        // Iterate over the common years and create data points
        for (String year : commonYears) {
            if (!year.matches("\\d{4}") || !isInRange(year, minYear, maxYear)) {
                continue;
            }
            Map<String, Object> dataPoint = new HashMap<>();
            dataPoint.put("year", year);
            dataPoint.put("x", data1.get(year));
            dataPoint.put("y", data2.get(year));
            dataPoints.add(dataPoint);
        }

        // Put the data points list in the result map
        resultMap.put("data", dataPoints);

        System.out.println(resultMap);

        scatterResponseDTO.setCountry(resultMap.get("iso_code").toString());
        scatterResponseDTO.setDataPoints(dataPoints);
        return scatterResponseDTO;
    
        
    }

    private static boolean isInRange(String year, int start, int end) {
        int yearInt = Integer.parseInt(year);
        return yearInt >= start && yearInt <= end;
    }

    @Override
    public String toString() {
        return "ScatterResponseDTO [country=" + country + ", dataPoints=" + dataPoints + "]";
    }


    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public List<Map<String, Object>> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<Map<String, Object>> dataPoints) {
        this.dataPoints = dataPoints;
    }



}

