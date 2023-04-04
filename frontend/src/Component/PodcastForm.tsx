import React, {ChangeEvent} from 'react';
import UseSendUrl from "../Hooks/UseSendUrl";


function PodcastForm() {
    const {handleSubmit, podcast, setPodcast, loading, setLoading, response} = UseSendUrl();

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        console.log(event.target.value)
        setPodcast(event.target.value);
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label>
                    Podcast:
                    <input type="text" value={podcast} onChange={handleChange}/>
                </label>
                <button type="submit">Submit</button>
            </form>
            {loading && <p>Loading...</p>}
            {response && (
                <pre>
                    {JSON.stringify(response, null, 2)}
                </pre>
            )}
        </div>
    );
}

export default PodcastForm;
