import React, {ChangeEvent} from 'react';
import UseSendUrl from "../Hooks/UseSendUrl";
import {useNavigate} from "react-router-dom";


function PodcastForm() {

    const {podcast, setPodcast} = UseSendUrl();
    const navigate = useNavigate();

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        console.log(event.target.value)
        setPodcast(event.target.value);
    };

    function handleSubmit() {

        navigate("/podcast")
    }

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
