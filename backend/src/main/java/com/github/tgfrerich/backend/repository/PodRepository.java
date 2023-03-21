package com.github.tgfrerich.backend.repository;


import com.github.tgfrerich.backend.model.Podcast;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PodRepository extends MongoRepository<Podcast, String> {
}
