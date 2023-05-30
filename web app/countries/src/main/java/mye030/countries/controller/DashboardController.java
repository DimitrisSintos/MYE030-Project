package mye030.countries.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import mye030.countries.model.Country;
import mye030.countries.model.Option;
import mye030.countries.service.countryService;

@Controller
public class DashboardController {
    @Autowired
    private countryService countryService;

    @RequestMapping("/")
    public String dashboard(Model model) throws Exception{

        List<Country> countries = countryService.getAllCountries();
        // System.out.println(countries);


        model.addAttribute("countries", countries);

        
        return "dashboard";
    }


}
