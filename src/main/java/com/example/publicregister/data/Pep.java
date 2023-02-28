package com.example.publicregister.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document
public class Pep {
    @Id
    private String id;

    @Field("type_of_official")
    private String typeOfOfficial;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("related_persons")
    private List<RelatedPerson> relatedPersons;

    @Field("is_pep")
    private boolean isPep;

    private String photo;

    @Field("full_name_en")
    private String fullNameEn;

    @Field("first_name_en")
    private String firstNameEn;

    @Field("last_name_en")
    private String lastNameEn;

    private String url;

    @Field("date_of_birth")
    private String dateOfBirth;

    @Field("type_of_official_en")
    private String typeOfOfficialEn;

    @Field("full_name")
    private String fullName;

    private String patronymic;

    @Field("patronymic_en")
    private String patronymicEn;

    private boolean died;

    @Field("also_known_as_en")
    private String alsoKnownAsEn;

    private String names;
}
