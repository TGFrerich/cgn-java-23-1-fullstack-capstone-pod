import React, {useEffect} from "react";
import UseSendUrl from "../Hooks/UseSendUrl";
import {useNavigate} from "react-router-dom";

function PodcastForm() {
    const {podcast, setPodcast, loading, setLoading, response, sendUrl} = UseSendUrl();
    const navigate = useNavigate();


    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            await sendUrl(podcast);
            setLoading(false);
        };
        fetchData();
    }, []);


    return (
        <div>
            {loading && <p>Loading...</p>}
            {response && <pre>{JSON.stringify(response, null, 2)}</pre>}
        </div>
    );
}

export default PodcastForm;
