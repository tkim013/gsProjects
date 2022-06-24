import "./App.css";
import { BrowserRouter } from "react-router-dom";

//file imports
import Main from "./components/Main";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Main />
      </BrowserRouter>
    </div>
  );
}

export default App;