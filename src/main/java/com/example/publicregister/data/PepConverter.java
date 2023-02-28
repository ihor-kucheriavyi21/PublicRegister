package com.example.publicregister.data;

import java.util.ArrayList;
import java.util.List;

public class PepConverter {

    public static PepResponseDTO convertToDTO(Pep data) {
        PepResponseDTO responseDTO = new PepResponseDTO();
        responseDTO.setFirstName(data.getFirstName());
        responseDTO.setLastName(data.getLastName());
        responseDTO.setFullName(data.getFullName());
        responseDTO.setDateOfBirth(data.getDateOfBirth());
        responseDTO.setDied(data.isDied());

        List<RelatedPersonDTO> relatedPersonDTOs = new ArrayList<>();
        for (RelatedPerson relatedPerson : data.getRelatedPersons()) {
            RelatedPersonDTO relatedPersonDTO = new RelatedPersonDTO();
            relatedPersonDTO.setRelationshipTypeEn(relatedPerson.getRelationshipTypeEn());
            relatedPersonDTO.setPersonEn(relatedPerson.getPersonEn());
            relatedPersonDTO.setPersonUk(relatedPerson.getPersonUk());
            relatedPersonDTO.setRelationshipType(relatedPerson.getRelationshipType());
            relatedPersonDTOs.add(relatedPersonDTO);
        }
        responseDTO.setRelatedPersons(relatedPersonDTOs);

        return responseDTO;
    }

}
