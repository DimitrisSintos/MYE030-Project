package mye030.countries.dto;

import java.util.List;
import java.util.Map;

public class ScatterRequestDTO {

    private String fixedField;
    private Map<String, String> xField;
    private Map<String, String> yField;
    private List<String> countries;
    private YearRangeDTO years;

    
    @Override
    public String toString() {
        return "ScatterRequestDTO [countries=" + countries + ", xField=" + xField + ", yField=" + yField + ", years="
                + years + "]";
    }


    public String getFixedField() {
        return fixedField;
    }


    public void setFixedField(String fixedField) {
        this.fixedField = fixedField;
    }


    public Map<String, String> getxField() {
        return xField;
    }


    public void setxField(Map<String, String> xField) {
        this.xField = xField;
    }


    public Map<String, String> getyField() {
        return yField;
    }


    public void setyField(Map<String, String> yField) {
        this.yField = yField;
    }


    public List<String> getCountries() {
        return countries;
    }


    public void setCountries(List<String> countries) {
        this.countries = countries;
    }


    public YearRangeDTO getYears() {
        return years;
    }


    public void setYears(YearRangeDTO years) {
        this.years = years;
    }



  

}



