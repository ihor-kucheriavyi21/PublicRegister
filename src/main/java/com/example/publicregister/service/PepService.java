package com.example.publicregister.service;

import com.example.publicregister.data.PopularNameResponseDTO;
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

    @Autowired
    public PepService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
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
}
