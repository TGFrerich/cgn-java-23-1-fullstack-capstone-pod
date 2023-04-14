import React from 'react';
import {TranscribedPodcastFromAssemblyAI} from '../Model/TransribedPodcastFromAssemblyAI';
import "../Styling/PodCastCard.css"

type PodcastCardProps = {
    podcastData: TranscribedPodcastFromAssemblyAI;
};

function PodcastCard({podcastData}: PodcastCardProps) {
    console.log('PodcastData object:', podcastData); // Keep this line to log the podcastData object

    const formatDuration = (duration: number) => {
        const hours = Math.floor(duration / 3600);
        const minutes = Math.floor((duration % 3600) / 60);
        return `${hours.toString().padStart(2, '0')}h${minutes.toString().padStart(2, '0')}m`;
    };



    return (
        <div className="podcast-card">
            <h3>Audio Duration: {formatDuration(podcastData.audio_duration)}</h3>
            <h4>Conversation</h4>
            {podcastData.utterances && podcastData.utterances.map((utterance, index) => (
                <div key={index}>
                    <div className="podcast-card-speaker"><strong>Speaker {utterance.speaker}:</strong></div>
                    <div className="podcast-card-utterance"><p>{utterance.text}</p></div>

                </div>
            ))}
        </div>
    );
}

export default PodcastCard;
