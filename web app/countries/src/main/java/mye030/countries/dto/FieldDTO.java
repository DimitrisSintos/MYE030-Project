package mye030.countries.dto;

import java.util.List;

public class FieldDTO {
    private List<String> x;
    private List<String> y;
    public List<String> getX() {
        return x;
    }
    public void setX(List<String> x) {
        this.x = x;
    }
    public List<String> getY() {
        return y;
    }
    public void setY(List<String> y) {
        this.y = y;
    }
    @Override
    public String toString() {
        return "FieldDTO [x=" + x + ", y=" + y + "]";
    }
}
