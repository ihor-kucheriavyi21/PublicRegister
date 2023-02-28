package com.example.publicregister.repository;

import com.example.publicregister.data.Pep;
import com.example.publicregister.data.PopularNameResponseDTO;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PepRepository extends MongoRepository<Pep, String> {
    Pep findByFullNameEn(String fullNameEn);

    Pep findByFullName(String fullName);

    @Aggregation("{ $group: { _id: \"$firstName\", quantity: { $sum: 1 } } }, { $sort: { quantity: -1 } }, { $limit: ?0 }")
    List<PopularNameResponseDTO> findTop10PopularNames(int limit);
}
