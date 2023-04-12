import React from 'react';
import {TranscribedPodcastFromAssemblyAI} from '../Model/TransribedPodcastFromAssemblyAI';

type PodcastCardProps = {
    podcastData: TranscribedPodcastFromAssemblyAI;
};

function PodcastCard({podcastData}: PodcastCardProps) {
    console.log('PodcastData object:', podcastData); // Keep this line to log the podcastData object

    const formatDuration = (duration: number) => {
        const hours = Math.floor(duration / 3600);
        const minutes = Math.floor((duration % 3600) / 60);
        return `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}`;
    };

    // if (!podcastData) {
    //     return <p>Loading podcast data...</p>;
    // }

    return (
        <div className="podcast-card">
            <h3>Audio Duration: {formatDuration(podcastData.audio_duration)}</h3>
            <h4>Utterances</h4>
            {podcastData.utterances && podcastData.utterances.map((utterance, index) => (
                <div key={index} style={{marginBottom: '1rem'}}>
                    <strong>Speaker {utterance.speaker}:</strong>
                    <p>{utterance.text}</p>
                </div>
            ))}
        </div>
    );
}

export default PodcastCard;
