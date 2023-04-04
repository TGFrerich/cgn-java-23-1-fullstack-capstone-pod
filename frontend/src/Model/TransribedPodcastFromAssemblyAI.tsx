import {UtterancesFromSpeakersInTranscribedPodcast} from "./UtterancesFromSpeakersInTranscribedPodcast";
import {ChaptersInTranscribedPodcast} from "./ChaptersInTranscribedPodcast";

export type TranscribedPodcastFromAssemblyAI = {
    id: string,
    audio_url: string,
    status: string,
    audio_duration: number,
    acoustic_model: string,
    language_model: string,
    language_code: string,
    format_text: boolean
    punctuate: boolean,
    utterances: UtterancesFromSpeakersInTranscribedPodcast[],
    auto_chapters: boolean,
    chapters: ChaptersInTranscribedPodcast[],


}