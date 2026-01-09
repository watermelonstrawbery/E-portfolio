import askAI from "./TesterStuff/askAI";
import { useState, useEffect } from 'react';


function EvaluateButton(){
    const [response, setResponse] = useState("404");

    return <div>
        <button onClick = {evaluate}>Evaluate</button>
        <div>{response}</div>
    </div>



    function evaluate(){
        askAI("generate three random words").then(receiveResponse)

        function receiveResponse(received){
            console.log(received);
            
            setResponse(received.response.text());
        }
    }
}

export default EvaluateButton;