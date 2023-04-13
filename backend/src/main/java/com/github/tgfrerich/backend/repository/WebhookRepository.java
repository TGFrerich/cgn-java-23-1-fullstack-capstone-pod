package com.github.tgfrerich.backend.repository;


import com.github.tgfrerich.backend.model.AssemblyAIWebhook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebhookRepository extends MongoRepository<AssemblyAIWebhook, String> {

}