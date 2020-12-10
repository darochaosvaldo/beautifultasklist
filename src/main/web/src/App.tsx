import React from 'react';
import logo from './logo.svg';
import './App.css';

function App() {

  async function testTasks() {
    let response = await fetch("/api/tasks");
    let body = await response.json();
    console.log(body)
  }

  testTasks();

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h1>
          Hello World!
        </h1>
      </header>
    </div>
  );
}

export default App;
