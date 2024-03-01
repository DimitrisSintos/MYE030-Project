package mye030.countries.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mye030.countries.dto.FieldOption;

@Service
public class fieldOptionsServiceImpl implements fieldOptionsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<FieldOption> getfieldOptions() {
        List<String> tableNames = jdbcTemplate.queryForList("SELECT table_name FROM information_schema.tables WHERE table_name != 'countries' AND table_schema='public';", String.class);
        List<FieldOption> fieldOptions =  new ArrayList<FieldOption>();


        for (String tableName : tableNames) {
            // check if tables has a "field" column
            Boolean hasFieldColumn = jdbcTemplate.queryForObject("SELECT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = ? AND column_name = 'field');", Boolean.class, tableName);
            if (hasFieldColumn) {
                List<String> fieldNames = jdbcTemplate.queryForList("SELECT DISTINCT field FROM " + tableName + ";", String.class);
                for (String fieldName : fieldNames) {
                    fieldOptions.add(new FieldOption(tableName+"$"+fieldName, formatName(fieldName)));
                }
            }
            else {
                fieldOptions.add(new FieldOption(tableName+"$"+tableName, formatName(tableName)));
            }


        }

        return fieldOptions;
    }
    
    public String formatName(String tableName) {
        // Split the string by underscores
        String[] words = tableName.split("_");
    
        // Capitalize the first letter of each word
        for (int i = 0; i < words.length; i++) {
            words[i] = Character.toUpperCase(words[i].charAt(0)) + words[i].substring(1);
        }
    
        // Join the words back together with a space
        return String.join(" ", words);
    }

}
