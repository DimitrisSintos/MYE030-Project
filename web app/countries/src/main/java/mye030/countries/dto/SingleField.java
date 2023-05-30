package mye030.countries.dto;

public class SingleField {
    private String tableName;
    private String fieldName;


    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return "SingleField [tableName=" + tableName + ", fieldName=" + fieldName + "]";
    }
}
