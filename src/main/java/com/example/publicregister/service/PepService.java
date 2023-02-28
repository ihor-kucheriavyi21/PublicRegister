package com.example.publicregister.service;

import com.example.publicregister.data.Pep;
import com.example.publicregister.data.PopularNameResponseDTO;
import com.example.publicregister.repository.PepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PepService {

    private final MongoTemplate mongoTemplate;

    private final PepRepository pepRepository;

    @Autowired
    public PepService(MongoTemplate mongoTemplate, PepRepository pepRepository) {
        this.mongoTemplate = mongoTemplate;
        this.pepRepository = pepRepository;
    }

    public List<PopularNameResponseDTO> findPopularPepByFirstName(int limit) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("is_pep").is(true)),
                Aggregation.group("first_name").count().as("quantity"),
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "quantity")),
                Aggregation.limit(limit)
        );

        AggregationResults<PopularNameResponseDTO> results = mongoTemplate.aggregate(aggregation, "pep", PopularNameResponseDTO.class);
        return results.getMappedResults();
    }

    public Pep findByFullNameEn(String fullNameEn) {
        return pepRepository.findByFullNameEn(fullNameEn);
    }

    public Pep findByFullName(String fullName) {
        return pepRepository.findByFullName(fullName);
    }
}
