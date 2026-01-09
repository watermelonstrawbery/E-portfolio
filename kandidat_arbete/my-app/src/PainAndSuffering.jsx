import { useState, useEffect } from 'react';

function PainAndSuffering(){

    const [word, setWord] = useState("404");

    useEffect(() => {
        console.log("FETCHING");
        
        fetch("https://random-word-api.vercel.app/api?words=1").then(resolve);
    }, []);
    
    function resolve(argument){
        console.log("Argument");
        console.log(argument);

        argument.json().then(innerResolve);

        function innerResolve(argument){
            console.log("Inner argument");
            console.log(argument);

            setWord(argument[0]);
        }
    }

    return <div>
        Text:
        {word}
    </div>
    
}

export default PainAndSuffering;