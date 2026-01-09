import { useState, useEffect } from 'react';

function AnswerQuestionWindow(props){

    const [questionNumber, setQuestionNumber] = useState(0);
    const [answer, setAnswer] = useState("");

    async function nextQuestion(){

        props.qa[questionNumber].setAnswer(answer);

        setAnswer("");

        if(questionNumber < props.qa.length - 1){

            setQuestionNumber(questionNumber + 1);
            
        } else {
            console.log("Done");
            console.log(props.qa);
        }
    }

    return <div>
        <div>Question: {props.qa[questionNumber].question}</div>

        <input value={answer} onChange={(e) => setAnswer(e.target.value)}></input><br/>

        <button onClick={nextQuestion}>Next Question</button><br/><br/>

        
        
    </div>
    
}

export default AnswerQuestionWindow;