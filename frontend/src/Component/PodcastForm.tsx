import React, {ChangeEvent, FormEvent, useState} from 'react';
import axios from 'axios';

function PodcastForm() {
    const [podcast, setPodcast] = useState('');

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            await axios.post('/api/podcasts', {podcast});
            setPodcast('');
        } catch (error) {
            console.error(error);
        }
    };

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setPodcast(event.target.value);
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Podcast:
                <input type="text" value={podcast} onChange={handleChange}/>
            </label>
            <button type="submit">Submit</button>
        </form>
    );
}

export default PodcastForm;
