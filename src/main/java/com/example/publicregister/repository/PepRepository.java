package com.example.publicregister.repository;

import com.example.publicregister.data.Pep;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PepRepository extends MongoRepository<Pep, String> {
    Pep findByFullNameEn(String fullNameEn);

    Pep findByFullName(String fullName);

}
