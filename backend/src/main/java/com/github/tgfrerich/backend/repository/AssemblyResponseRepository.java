package com.github.tgfrerich.backend.repository;

import com.github.tgfrerich.backend.model.AssemblyAIApiResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssemblyResponseRepository extends MongoRepository<AssemblyAIApiResponse, String> {

}