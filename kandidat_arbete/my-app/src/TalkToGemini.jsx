import { useState, useEffect } from 'react';

function TalkToGemini(){

    const [response, setResponse] = useState("404");
    const [prompt, setPrompt] = useState("Empty");

    async function AskAI(){
        const { GoogleGenerativeAI } = require("@google/generative-ai");
          
        const genAI = new GoogleGenerativeAI("AIzaSyDSsPu7iKIYT26cl25oImCmynkusxdfj4I");
        const model = genAI.getGenerativeModel({ model: "gemini-1.5-flash" });
          
        const prompt = "Tell me a short joke";
        
        
        const result = await model.generateContent(prompt);
        console.log("Setting response");
        
        console.log(result.response.text());
        setPrompt(prompt);
        setResponse(result.response.text());
    }

    async function TalkToEcho(){
        var url = "https://api.genderize.io?name=";
        console.log(prompt);
        
        url = url + prompt;

        const result = await (await fetch(url)).json();
        console.log(result);

        setResponse(result.gender);
    }

    return <div>
        <input value={prompt} onChange={(e) => setPrompt(e.target.value)}></input><br/>

        <button onClick={TalkToEcho}>Ask Echo</button><br/><br/>

        Response: {response}

        
        
    </div>
    
}

export default TalkToGemini;