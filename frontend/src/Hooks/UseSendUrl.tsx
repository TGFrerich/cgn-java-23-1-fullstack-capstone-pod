import {FormEvent, useState} from 'react';
import {TranscribedPodcastFromAssemblyAI} from '../Model/TransribedPodcastFromAssemblyAI';
import axios from 'axios';

export default function UseSendUrl() {
    const [podcast, setPodcast] = useState('');
    const [loading, setLoading] = useState(false);
    const [response, setResponse] = useState<TranscribedPodcastFromAssemblyAI | null>(null);

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setLoading(true);
        await axios
            .post('/api/podcasts', {
                audio_url: podcast,
                language_code: '',
            })
            .then((result) => {
                setPodcast('');
                const s = result.data.substring(5)
                console.log(s)
                setResponse(JSON.parse(result.data.substring(5)));
                console.log('Reponse object in UseSendUrl', result.data);
            })
            .catch((error) => {
                console.error('API error:', error); // Log the API error
            })
            .finally(() => {
                setLoading(false);
            });
    };

    return {handleSubmit, podcast, setPodcast, loading, setLoading, response};
}
