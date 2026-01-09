import { useState, useEffect } from 'react';
import { csvToObject } from './TesterStuff/csvExtractor';

function TestFetchButton(){

    const [response, setResponse] = useState("404");

    
    console.log(response);
    

    return <div>
        <button onClick={askCSV}>Do the thing</button>
        <div>{response}</div>
    </div>

    function askCSV(){
        setResponse(csvToObject("responses.csv").response);
    }
}

export default TestFetchButton;