package mye030.countries.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import mye030.countries.model.Country;
import mye030.countries.service.countryService;
import mye030.countries.service.fieldOptionsService;
import mye030.countries.dto.FieldOption;

@Controller
public class DashboardController {
    @Autowired
    private countryService countryService;

    @Autowired
    private fieldOptionsService fieldOptionsService;

    @RequestMapping("/")
    public String dashboard(Model model) throws Exception{

        List<Country> countries = countryService.getAllCountries();
        List<FieldOption> fieldOptions = fieldOptionsService.getfieldOptions();


        // System.out.println(countries);
        // System.out.println(fieldOptions);

        model.addAttribute("countries", countries);
        model.addAttribute("fieldOptions", fieldOptions);

        
        return "dashboard";
    }


}
