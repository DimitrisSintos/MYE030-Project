package mye030.countries.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface metricsRepository {
    List<Map<String, Object>> findGdpPerCapitaByCountries(List<String> countryIsoCode);
    
}
