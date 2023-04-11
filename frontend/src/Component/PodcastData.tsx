import React from 'react';
import PodcastCard from "../Model/PodcastCard";
import {TranscribedPodcastFromAssemblyAI} from '../Model/TransribedPodcastFromAssemblyAI';

type PodcastDataProps = {
    response: TranscribedPodcastFromAssemblyAI | null;
};

function PodcastData({response}: PodcastDataProps) {
    console.log("Response object:", response); // Add this line to log the response object

    if (!response) {
        return null;
    }

    return (
        <div>
            <h2>Podcast Data</h2>
            <PodcastCard podcastData={response}/>
        </div>
    );
}

export default PodcastData;
