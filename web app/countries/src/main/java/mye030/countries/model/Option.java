package mye030.countries.model;

public class Option {
    private String value;
    private String innerText;
    

    public Option(String value, String innerText) {
        this.value = value;
        this.innerText = innerText;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getInnerText() {
        return innerText;
    }
    public void setInnerText(String innerText) {
        this.innerText = innerText;
    }

    @Override
    public String toString() {
        return "Option [value=" + value + ", innerText=" + innerText + "]";
    }
}
