import './App.css'
import axios from 'axios'
import { useState } from 'react'

function App() {
 
  const [quote, setQuote] = useState('')

  return (
    <div className="App">
      <h1>HI</h1>

      <h1>{quote}</h1>

      <button onClick={() => axios.get('https://api.kanye.rest').then(result => setQuote(result.data.quote))}>
        Test Me
      </button>
    </div>
  )
}

export default App
