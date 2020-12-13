import React, { useEffect, useState } from 'react';
import './App.css';
import { Checkbox } from '@material-ui/core';

function App() {

  const [data, setData] = useState<any[]>([]);

  useEffect(() => {
    fetch("/tasklist")
      .then((response) => response.json())
      .then((json) => {
        setData([...json]);
        // Aux
        console.log(json);
      });
  }, []);

  return (
    <div className="App">
      <ul>
        {data.map(item => (
          <li>
            {item.title}
            <Checkbox value={item.status} />
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
