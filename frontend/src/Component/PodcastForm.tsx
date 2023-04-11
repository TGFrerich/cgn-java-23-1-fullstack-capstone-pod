import React, {ChangeEvent} from 'react';
import UseSendUrl from '../Hooks/UseSendUrl';
import UrlInput from './UrlInput';
import PodcastData from './PodcastData';

function PodcastForm() {
    const {handleSubmit, podcast, setPodcast, response, loading} = UseSendUrl(); // Add loading here

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setPodcast(event.target.value);
    };

    if (loading) {
        return <p>Loading...</p>;
    }

    if (response) {
        return <PodcastData response={response}/>;
    }

    return <UrlInput value={podcast} onChange={handleChange} onSubmit={handleSubmit}/>;
}

export default PodcastForm;
