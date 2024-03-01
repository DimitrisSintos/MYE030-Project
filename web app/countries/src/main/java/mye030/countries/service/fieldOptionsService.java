package mye030.countries.service;

import java.util.List;

import org.springframework.stereotype.Service;


import mye030.countries.dto.FieldOption;

@Service
public interface fieldOptionsService {
    List<FieldOption> getfieldOptions();
}