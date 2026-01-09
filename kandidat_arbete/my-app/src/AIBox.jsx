import { useState, useEffect } from 'react';

function AIBox(){

    const [response, setResponse] = useState("404");

    async function AskAI(){
        const { GoogleGenerativeAI } = require("@google/generative-ai");
          
        const genAI = new GoogleGenerativeAI("AIzaSyDSsPu7iKIYT26cl25oImCmynkusxdfj4I");
        const model = genAI.getGenerativeModel({ model: "gemini-1.5-flash" });
          
        const prompt = "Tell me a short joke";
          
        const result = await model.generateContent(prompt);
        console.log("Setting response");
        
        console.log(result.response.text());
        setResponse(result.response.text());
    }

    return <div>
        Text:
        {response}

        <button onClick={AskAI}>Ask Gemini!</button>
    </div>
    
}

export default AIBox;