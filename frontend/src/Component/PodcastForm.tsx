import React, {ChangeEvent, useEffect} from 'react';
import UseSendUrl from "../Hooks/UseSendUrl";
import LoadingScreen from "./LoadingScreen";
import PodcastData from "./PodcastData";
import UrlInput from "./UrlInput";

function PodcastForm() {
    const {handleSubmit, podcast, setPodcast, loading, setLoading, response} = UseSendUrl();

    useEffect(() => {
        if (response) {
            setLoading(false);
        }
    }, [response, setLoading]);

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        console.log(event.target.value);
        setPodcast(event.target.value);
    };

    if (loading) {
        return <LoadingScreen/>;
    }

    if (response) {
        return <PodcastData response={response}/>;
    }

    return <UrlInput value={podcast} onChange={handleChange} onSubmit={handleSubmit}/>;
}

export default PodcastForm;
