package mye030.countries.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mye030.countries.dto.TimeseriesRequestDTO;
import mye030.countries.dto.TimeseriesResponseDTO;
import mye030.countries.dto.ScatterRequestDTO;
import mye030.countries.dto.ScatterResponseDTO;
import mye030.countries.service.countryService;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private countryService countryService;

    @PostMapping("/getTimeseriesData")
    public ResponseEntity<TimeseriesResponseDTO> getTimeseriesData(@RequestBody TimeseriesRequestDTO request) {

        // Process the request and generate the response
        TimeseriesResponseDTO response = countryService.processTimeseriesRequest(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getScatterData")
    public ResponseEntity<ScatterResponseDTO> getScatterData(@RequestBody ScatterRequestDTO request) {

        // Process the request and generate the response
        ScatterResponseDTO response = countryService.processScatterRequest(request);
        return ResponseEntity.ok(response);
    }
 
    
    
}
