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
          <a
              className="App-link"
              href="https://reactjs.org"
              target="_blank"
              rel="noopener noreferrer"
          >
              Search for Podcasts
          </a>
      </header>
        <PodcastForm/>
    </div>
  );
}

export default App;
