package mye030.countries.dto;

public class YearRangeDTO {
    private String min;
    private String max;


    public String getMin() {
        return min;
    }
    public void setMin(String min) {
        this.min = min;
    }
    public String getMax() {
        return max;
    }
    public void setMax(String max) {
        this.max = max;
    }
    @Override
    public String toString() {
        return "YearRangeDTO [min=" + min + ", max=" + max + "]";
    }

}
