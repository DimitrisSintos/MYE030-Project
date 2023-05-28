package mye030.countries.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mye030.countries.dto.RequestDTO;

import mye030.countries.dto.ResponseDTO;
import mye030.countries.model.Country;
import mye030.countries.service.countryService;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private countryService countryService;

    @PostMapping("/getTimeseriesData")
    public ResponseEntity<ResponseDTO> getTimeseriesData(@RequestBody RequestDTO request) {
        // Process the request and generate the response

        ResponseDTO response = countryService.processRequest(request);
        

        return ResponseEntity.ok(response);
    }
    
    
}
