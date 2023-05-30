package mye030.countries.dto;

import java.util.List;
import java.util.Map;

public class TimeseriesRequestDTO {
    private List<String> countries;
    private Map<String, List<String>> fields;
    private YearRangeDTO years;


    @Override
    public String toString() {
        return "RequestDTO [countries=" + countries + ", fields=" + fields + ", years=" + years + "]";
    }


    public List<String> getCountries() {
        return countries;
    }


    public void setCountries(List<String> countries) {
        this.countries = countries;
    }


    public Map<String, List<String>> getFields() {
        return fields;
    }


    public void setFields(Map<String, List<String>> fields) {
        this.fields = fields;
    }


    public YearRangeDTO getYears() {
        return years;
    }


    public void setYears(YearRangeDTO years) {
        this.years = years;
    }


  

}



