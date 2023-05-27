package mye030.countries.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;

public class ResponseDTO {
    private Map<String, Map<String, FieldDTO>> data;


    // mapper method to turn a List<Map<String, Object>> into a ResponseDTO
    public static ResponseDTO mapToResponseDTO(List<Map<String, Object>> result) {
        if (result.isEmpty()) {
            // Handle empty result case if needed
            return null;
        }
        ResponseDTO responseDTO = new ResponseDTO();
        Map<String, Map<String, FieldDTO>> data = new HashMap<>();


        
        for (Map<String, Object> row : result) {
            String isoCode = row.get("iso_code").toString();
            
            // add new entry for the current iso_code
            if (!data.containsKey(isoCode)) {
                data.put(isoCode, new HashMap<>());
            }

            FieldDTO fieldDTO = new FieldDTO();
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String year = entry.getKey();
                Object value = entry.getValue();

                if (year.matches("\\d{4}")) {
                    String fieldName = year;

                    List<String> xValues = fieldDTO.getX();
                    List<String> yValues = fieldDTO.getY();

                    if (xValues == null || yValues == null) {
                        xValues = new ArrayList<>();
                        yValues = new ArrayList<>();
                    }

                    xValues.add(fieldName);

                    if (value == null) {
                        yValues.add("null");
                    } 
                    // if data for a year is not available, the value is null
                    else {
                        yValues.add(value.toString());
                    }

                    fieldDTO.setX(xValues);
                    fieldDTO.setY(yValues);

                }

            data.get(isoCode).put("gdp_capita", fieldDTO);

            }
        }

        responseDTO.setData(data);
        return responseDTO;
    }


    public Map<String, Map<String, FieldDTO>> getData() {
        return data;
    }


    public void setData(Map<String, Map<String, FieldDTO>> data) {
        this.data = data;
    }




}
