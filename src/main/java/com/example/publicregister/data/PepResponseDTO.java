package com.example.publicregister.data;

import lombok.Data;

import java.util.List;

@Data
public class PepResponseDTO {
    private String firstName;
    private String lastName;
    private String fullName;
    private String dateOfBirth;
    private boolean died;
    private List<RelatedPersonDTO> relatedPersons;
}
