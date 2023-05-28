package mye030.countries.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class metricsRepositoryImpl implements metricsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> findGdpPerCapitaByCountries(List<String> isoCodeList) {
        String query = "SELECT * FROM gdp_capita WHERE iso_code in (" + String.join(",", isoCodeList) + ");";
        return jdbcTemplate.queryForList(query);
    }

    @Override
    public List<Map<String, Object>> findDataByCountryAndFields(String countryIso, List<String> fieldNames, String tableName) {
        String query = "";

        if (fieldNames.isEmpty()) {
            query = "SELECT * FROM " + tableName + " WHERE iso_code in (" + countryIso + ");";
        }
        else {
            query = "SELECT * FROM " + tableName + " WHERE iso_code in (" + countryIso + ") AND field in ('" + String.join("','", fieldNames) + "');";
        }

        // TODO: treat the 2 special tables differently
        
        return jdbcTemplate.queryForList(query);
    }
    
}
