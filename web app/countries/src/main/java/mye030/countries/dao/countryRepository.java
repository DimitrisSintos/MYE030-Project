package mye030.countries.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mye030.countries.model.Country;

public interface countryRepository extends JpaRepository<Country, Integer>{
    List<Country> findAll();
}
