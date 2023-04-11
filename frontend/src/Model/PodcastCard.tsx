import React from 'react';
import {TranscribedPodcastFromAssemblyAI} from '../Model/TransribedPodcastFromAssemblyAI';

type PodcastCardProps = {
    podcastData: TranscribedPodcastFromAssemblyAI;
};

function PodcastCard({podcastData}: PodcastCardProps) {
    // Display the podcast data in a card format
    return (
        <div className="podcast-card">
            {/* Display the podcast data here */}
        </div>
    );
}

export default PodcastCard;
