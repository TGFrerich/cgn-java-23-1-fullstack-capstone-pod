import {useCallback, useState} from "react";
import {TranscribedPodcastFromAssemblyAI} from "../Model/TransribedPodcastFromAssemblyAI";
import axios from "axios";

export default function UseSendUrl() {
    const [podcast, setPodcast] = useState("");
    const [loading, setLoading] = useState(false);
    const [response, setResponse] = useState<TranscribedPodcastFromAssemblyAI | null>(
        null
    );

    const sendUrl = useCallback(async (url: string) => {
        setLoading(true);
        try {
            console.log("AXIOS")
            console.log(url)
            const result = await axios.post("/api/podcasts", {
                audio_url: url,
                language_code: "",
            });
            setPodcast("");
            setResponse(result.data);
        } catch (error) {
            console.error(error);
        } finally {
            setLoading(false);
        }
    }, []);

    return {sendUrl, podcast, setPodcast, loading, setLoading, response};
}
