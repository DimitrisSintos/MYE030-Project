package mye030.countries.dao;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

        if (fieldNames.isEmpty() || fieldNames == null || fieldNames.get(0).equals("")) {
            query = "SELECT * FROM " + tableName + " WHERE iso_code in (" + countryIso + ");";
        }
        else {
            query = "SELECT * FROM " + tableName + " WHERE iso_code in (" + countryIso + ") AND field in ('" + String.join("','", fieldNames) + "');";
        }

        return jdbcTemplate.queryForList(query);
    }

    @Override
    public List<Map<String, Object>> findDataByYearAndField(String year, String fieldName, String tableName, List<String> countries) {
        String query = "";

        if (fieldName == null || fieldName.equals("")) {
            query = "SELECT iso_code, \"" + year + "\", " + "\"" + year + "\" FROM " + tableName + " WHERE iso_code in ('" + String.join("','", countries) + "');";
        }
        else {
            query = "SELECT iso_code, \"" + year + "\", " + "\"" + year + "\" FROM " + tableName + " WHERE field='" + fieldName + "' AND iso_code in ('" + String.join("','", countries) +"');";
        }

        return jdbcTemplate.queryForList(query);
    }

   
}
