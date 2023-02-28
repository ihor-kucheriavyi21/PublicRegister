package com.example.publicregister.data;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class RelatedPerson {
    @Field("relationship_type_en")
    private String relationshipTypeEn;

    @Field("date_established")
    private String dateEstablished;

    @Field("person_en")
    private String personEn;

    @Field("date_confirmed")
    private String dateConfirmed;

    @Field("is_pep")
    private boolean isPep;

    @Field("date_finished")
    private String dateFinished;

    @Field("person_uk")
    private String personUk;

    @Field("relationship_type")
    private String relationshipType;

}

