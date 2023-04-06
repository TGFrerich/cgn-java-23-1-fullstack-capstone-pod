import React from 'react';
import './App.css';
import PodcastForm from "./Component/PodcastForm";
import {Route, Routes} from "react-router-dom";
import Header from "./Component/Header";
import ShowPodcasts from "./Component/ShowPodcasts";


function App() {
    return (
        <div className="App">

            <header className="App-header">
                <Header/>
                <Routes>
                    <Route path={"/"} element={<PodcastForm/>}/>
                    <Route path={"/podcast"} element={<ShowPodcasts/>}/>
                </Routes>

            </header>

        </div>
    );
}

export default App;
