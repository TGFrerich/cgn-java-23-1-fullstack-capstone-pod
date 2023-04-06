import logo from "../logo.svg";
import React from "react";

export default function Header() {
    return (<div><h1>Podcai</h1>
        <img src={logo} className="App-logo" alt="logo"/>
        <p>
            In the future you will be able to read your favourite Podcasts here!
        </p></div>)
}