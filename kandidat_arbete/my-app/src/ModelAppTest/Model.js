import { makeAutoObservable } from 'mobx';
import { csvToObject } from '../TesterStuff/csvExtractor';

export class Model {

    promiseStateTemplate = {
        promise: null,
        data: null,

        setPromise(prms){
            this.promise = prms;
        },

        setData(dat){
            this.data = dat;
        }
    }

  counter = 0;

  csvPromiseState = {...this.promiseStateTemplate}

  constructor() {
    makeAutoObservable(this);
  }

  setCounter(num) {
    this.counter = num;
    console.log("Counter function call");
    console.log(this.counter);
  }

  askCSV(){
    csvToObject(this.csvPromiseState, "responses.csv");
  }
}