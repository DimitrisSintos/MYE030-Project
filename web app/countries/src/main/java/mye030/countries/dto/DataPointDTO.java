package mye030.countries.dto;

public class DataPointDTO {
    private int year;
    private String country;
    private double x;
    private double y;


    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "DataPointDTO [year=" + year + ", country=" + country + ", x=" + x + ", y=" + y + "]";
    }
}
