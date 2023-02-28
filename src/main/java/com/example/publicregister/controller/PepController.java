package com.example.publicregister.controller;

import com.example.publicregister.data.Pep;
import com.example.publicregister.data.PepConverter;
import com.example.publicregister.data.PepResponseDTO;
import com.example.publicregister.data.PopularNameResponseDTO;
import com.example.publicregister.repository.PepRepository;
import com.example.publicregister.service.PepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pep")
public class PepController {

    private final PepRepository pepRepository;

    private final PepService pepService;

    @Autowired
    public PepController(PepRepository pepRepository, PepService pepService) {
        this.pepRepository = pepRepository;
        this.pepService = pepService;
    }

    @GetMapping("/all")
    public List<Pep> getAllPeps() {
        return pepRepository.findAll();
    }

    @GetMapping("/{fullNameEn}")
    public ResponseEntity<PepResponseDTO> getPepByFullNameEn(@PathVariable String fullNameEn) {
        Pep pep = pepRepository.findByFullNameEn(fullNameEn);
        if (pep != null) {
            return ResponseEntity.ok(PepConverter.convertToDTO(pep));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fullName/{fullName}")
    public ResponseEntity<PepResponseDTO> getPepByFullName(@PathVariable String fullName) {
        Pep pep = pepRepository.findByFullName(fullName);
        if (pep != null) {

            return ResponseEntity.ok(PepConverter.convertToDTO(pep));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/popularNames")
    public ResponseEntity<List<PopularNameResponseDTO>> getPopularNames() {
        List<PopularNameResponseDTO> result = pepService.findPopularPepByFirstName(10);
        System.out.println(result);
        return ResponseEntity.ok(result);
    }
}