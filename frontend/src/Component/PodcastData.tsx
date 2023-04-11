import React from 'react';
import {TranscribedPodcastFromAssemblyAI} from '../Model/TransribedPodcastFromAssemblyAI';

type PodcastDataProps = {
    data: TranscribedPodcastFromAssemblyAI;
};

function PodcastData({data}: PodcastDataProps) {
    return (
        <div>
            <h2>Podcast Data</h2>
            <h3>Utterances</h3>
            <div>
                {data.utterances && data.utterances.map((utterance, index) => (
                    <div key={index} style={{marginBottom: '1rem'}}>
                        <strong>Speaker {utterance.speaker}:</strong>
                        <p>{utterance.text}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default PodcastData;
