package mye030.countries.dto;

import java.util.*;


import org.apache.catalina.connector.Response;

public class ScatterResponseDTO {
    private String fixed;
    private List<Map<String, Object>> dataPoints;

    // mapper method to turn a List<Map<String, Object>> into a ResponseDTO
    public static ScatterResponseDTO mapToResponseDTO(Map<String, Object> data1, Map<String, Object> data2, int minYear, int maxYear) {
        ScatterResponseDTO scatterResponseDTO = new ScatterResponseDTO();

        if (data1 == null || data2 == null || data1.isEmpty() || data2.isEmpty()) {
            return scatterResponseDTO;
        }

        // Extract iso_code from data1
        String isoCode = String.valueOf(data1.get("iso_code"));

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

        scatterResponseDTO.setFixed(isoCode);
        scatterResponseDTO.setDataPoints(dataPoints);
        return scatterResponseDTO;
    
        
    }


    public static ScatterResponseDTO mapToResponseDTO(List<Map<String, Object>> data1, List<Map<String, Object>> data2, String year) {
        ScatterResponseDTO scatterResponseDTO = new ScatterResponseDTO();

        if (data1 == null || data2 == null || data1.isEmpty() || data2.isEmpty()) {
            return scatterResponseDTO;
        }

        // Extract the common countries between data1 and data2
        Set<String> commonCountries = new HashSet<>();
        for (Map<String, Object> row : data1) {
            commonCountries.add(String.valueOf(row.get("iso_code")));
        }
        for (Map<String, Object> row : data2) {
            commonCountries.add(String.valueOf(row.get("iso_code")));
        }

        // Create a list to hold the data points
        List<Map<String, Object>> dataPoints = new ArrayList<>();

        // Iterate over the common countries and create data points
        
        for (String country : commonCountries) {
            Map<String, Object> dataPoint = new HashMap<>();
            dataPoint.put("country", country);

            for (int i=0 ; i<data1.size() ; i++) {
                if (String.valueOf(data1.get(i).get("iso_code")).equals(country)) {
                    dataPoint.put("x", data1.get(i).get(year));
                    dataPoint.put("y", data2.get(i).get(year));
                }
            }


            dataPoints.add(dataPoint);
        }


        scatterResponseDTO.setFixed(year);
        scatterResponseDTO.setDataPoints(dataPoints);
        return scatterResponseDTO;
    
        
    }


    private static boolean isInRange(String year, int start, int end) {
        int yearInt = Integer.parseInt(year);
        return yearInt >= start && yearInt <= end;
    }

    @Override
    public String toString() {
        return "ScatterResponseDTO [fixed=" + fixed + ", dataPoints=" + dataPoints + "]";
    }


    public String getFixed() {
        return fixed;
    }
    
    public void setFixed(String value) {
        this.fixed = value;
    }
    
    public List<Map<String, Object>> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<Map<String, Object>> dataPoints) {
        this.dataPoints = dataPoints;
    }



}

