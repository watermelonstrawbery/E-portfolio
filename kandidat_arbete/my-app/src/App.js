import logo from './logo.svg';
import './App.css';

import PainAndSuffering from './PainAndSuffering';
import AIBox from './AIBox';
import TalkToGemini from './TalkToGemini';
import AnswerQuestionWindow from './AnswerQuestionsWindow';
import { csvToObject } from './TesterStuff/csvExtractor';
import EvaluateButton from './EvaluateButton';
import TestFetchButton from './TestFetchButton';
import ParentTest from './ModelAppTest/ParentTest';
import GrandParent from './ModelAppTest/GrandParent';

function App() {

  const answerObject = {
    question: null,
    answer: null,

    setQuestion(newQuestion){
      this.question = newQuestion;
    },

    setAnswer(newAnswer){
      this.answer = newAnswer;
    }
  }

  const questionArray = ["What is your favorite animal?", "What is your favorite color?", "What is your favorite movie"];
  const qaArray = [];

  for(var n = 0; n < questionArray.length; n++){
    const qa = {...answerObject};
    qa.setQuestion(questionArray[n]);
    qaArray.push(qa);
  }

  return (
    <div className="App">
      <PainAndSuffering/><br/><br/>
      
      <AnswerQuestionWindow qa = {qaArray}/>

      <GrandParent/>
    </div>
  );
}

export default App;
