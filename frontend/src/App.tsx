import React from 'react';
import logo from './logo.svg';
import './App.css';
import PodcastForm from "./Component/PodcastForm";

function App() {
  return (
    <div className="App">
      <header className="App-header">
          <h1>Podcai</h1>
          <img src={logo} className="App-logo" alt="logo"/>
          <p>
              In the future you will be able to read your favourite Podcasts here!
          </p>

      </header>
        <PodcastForm/>

    </div>
  );
}

export default App;
