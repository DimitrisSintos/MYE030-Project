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
    
}
