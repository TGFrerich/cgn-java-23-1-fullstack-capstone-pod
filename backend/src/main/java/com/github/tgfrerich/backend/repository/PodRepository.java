package com.github.tgfrerich.backend.repository;


import com.github.tgfrerich.backend.model.TranscribedPodcastFromAssemblyAI;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PodRepository extends MongoRepository<TranscribedPodcastFromAssemblyAI, String> {
    boolean existsById(String id);

    Optional<TranscribedPodcastFromAssemblyAI> findById(String id);

    @Query("{'audio_url': ?0}")
    boolean existsByAudio_Url(String audio_url);

    @Query("{'audio_url': ?0}")
    Optional<TranscribedPodcastFromAssemblyAI> findByAudioUrl(String audio_url);


}
