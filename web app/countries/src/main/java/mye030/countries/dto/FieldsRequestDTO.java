package mye030.countries.dto;

import java.util.List;

public class FieldsRequestDTO {
    private String tableName;
    private List<String> fieldNames;

   public String getTableName() {
        return tableName;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public List<String> getFieldNames() {
        return fieldNames;
    }


    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }


    @Override
    public String toString() {
        return "FieldsRequestDTO [tableName=" + tableName + ", fieldNames=" + fieldNames + "]";
    }
}
