package com.example.publicregister.controller;

import com.example.publicregister.data.Pep;
import com.example.publicregister.data.PepConverter;
import com.example.publicregister.data.PepResponseDTO;
import com.example.publicregister.data.PopularNameResponseDTO;
import com.example.publicregister.service.PepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pep")
public class PepController {

    private final PepService pepService;

    @Autowired
    public PepController(PepService pepService) {
        this.pepService = pepService;
    }

    @GetMapping("/{fullNameEn}")
    public ResponseEntity<PepResponseDTO> getPepByFullNameEn(@PathVariable String fullNameEn) {
        Pep pep = pepService.findByFullNameEn(fullNameEn);
        if (pep != null) {
            return ResponseEntity.ok(PepConverter.convertToDTO(pep));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fullName/{fullName}")
    public ResponseEntity<PepResponseDTO> getPepByFullName(@PathVariable String fullName) {
        Pep pep = pepService.findByFullName(fullName);
        if (pep != null) {

            return ResponseEntity.ok(PepConverter.convertToDTO(pep));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/popularNames")
    public ResponseEntity<List<PopularNameResponseDTO>> getPopularNames() {
        List<PopularNameResponseDTO> result = pepService.findPopularPepByFirstName(10);
        return ResponseEntity.ok(result);
    }
}