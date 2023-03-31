package com.github.tgfrerich.backend.repository;


import com.github.tgfrerich.backend.model.TranscribedPodcastFromAssemblyAI;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PodRepository extends MongoRepository<TranscribedPodcastFromAssemblyAI, String> {
    boolean existsById(String id);
    Optional<TranscribedPodcastFromAssemblyAI> findById(String id);

    boolean existsByAudioUrl(String audio_url);
    Optional <TranscribedPodcastFromAssemblyAI> findByAudioUrl(String audio_url);


}
