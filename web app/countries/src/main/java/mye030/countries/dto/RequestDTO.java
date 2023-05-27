package mye030.countries.dto;

import java.util.List;

public class RequestDTO {
    private List<String> countries;
    private List<String> fields;
    private YearRangeDTO years;


    public List<String> getCountries() {
        return countries;
    }
    public void setCountries(List<String> countries) {
        this.countries = countries;
    }
    public List<String> getFields() {
        return fields;
    }
    public void setFields(List<String> fields) {
        this.fields = fields;
    }
    public YearRangeDTO getYears() {
        return years;
    }
    public void setYears(YearRangeDTO years) {
        this.years = years;
    }
    @Override
    public String toString() {
        return "RequestDTO [countries=" + countries + ", fields=" + fields + ", years=" + years + "]";
    }

}



