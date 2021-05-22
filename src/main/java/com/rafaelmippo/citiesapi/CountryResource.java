package com.rafaelmippo.citiesapi;

import com.rafaelmippo.citiesapi.countries;
import com.rafaelmippo.citiesapi.repository;
import com.rafaelmippo.citiesapi.countries.Country;
import com.rafaelmippo.citiesapi.repository.CountryRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jdk.internal.net.http.ResponseEntity;
import jdk.jfr.internal.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@RestController
@RestMapping("/countries")
public class CountryResource {

    private CountryRepository repository;

    public CountryResource(CountryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<Country> countries(Pageable page) {
        
        return repository.findAll(page);
    }

    @GetMapping("/{id}")
    public Country getOne(@PathVariable Long id) {
        
        Optional<Country> optional = repository.findById(id);

        if(optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        }
        else {
            ResponseEntity.notFound().build();
        }

    }
}