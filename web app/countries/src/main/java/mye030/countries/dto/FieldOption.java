package mye030.countries.dto;


public class FieldOption {
    private String value;
    private String name;

    public FieldOption(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FieldOption [name=" + name + ", value=" + value + "]";
    }

}

