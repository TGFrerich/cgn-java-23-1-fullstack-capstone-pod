import {FormEvent, useEffect, useRef, useState} from 'react';
import {TranscribedPodcastFromAssemblyAI} from '../Model/TransribedPodcastFromAssemblyAI';
import axios from 'axios';

export default function UseSendUrl() {
    const [podcast, setPodcast] = useState('');
    const [loading, setLoading] = useState(false);
    const [response, setResponse] = useState<TranscribedPodcastFromAssemblyAI | null>(null);
    const isMounted = useRef(true);

    useEffect(() => {
        return () => {
            isMounted.current = false;
        };
    }, []);

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setLoading(true);
        await axios
            .post('/api/podcasts', {
                audio_url: podcast,
                language_code: '',
            })
            .then((result) => {
                console.log('API response:', result); // Log the API response
                if (isMounted.current) {
                    setPodcast('');
                    setResponse(result.data.data);
                }
            })
            .catch((error) => {
                if (isMounted.current) {
                    console.error('API error:', error); // Log the API error
                }
            })
            .finally(() => {
                if (isMounted.current) {
                    setLoading(false);
                }
            });
    };
    return {handleSubmit, podcast, setPodcast, loading, setLoading, response};
}
