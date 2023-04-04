import {FormEvent, useState} from "react";
import {TranscribedPodcastFromAssemblyAI} from "../Model/TransribedPodcastFromAssemblyAI";
import axios from "axios";

export default function UseSendUrl() {

    const [podcast, setPodcast] = useState('');
    const [loading, setLoading] = useState(false);
    const [response, setResponse] = useState<TranscribedPodcastFromAssemblyAI | null>(null);

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setLoading(true);
        await axios.post('/api/podcasts', podcast, {
            headers: {
                'Content-Type': 'text/plain',
            },
        })
            .then((result) => {
                setPodcast('');
                setResponse(result.data);
            })
            .catch((error) => {
                console.log(error.response.data.error);
            })
            .finally(() => {
                setLoading(false);
            });
    };
    return {handleSubmit, podcast, setPodcast, loading, setLoading, response}

}