package mye030.countries.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
    @RequestMapping("/")
    public String dashboard(Model model) {


        
        return "dashboard";
    }
    
}
