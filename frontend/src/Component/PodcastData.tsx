import React from 'react';
import {TranscribedPodcastFromAssemblyAI} from '../Model/TransribedPodcastFromAssemblyAI';
import PodcastCard from "../Model/PodcastCard";

type PodcastDataProps = {
    data: TranscribedPodcastFromAssemblyAI;
};

function PodcastData({data}: PodcastDataProps) {
    return (
        <div>
            <h2>Podcast Data</h2>
            <PodcastCard podcastData={data}/>
        </div>
    );
}

export default PodcastData;
