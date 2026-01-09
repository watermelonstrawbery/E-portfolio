function ChildB(props){
    return <div>
        ChildB<br/>
        <button onClick={increment}>Increment</button>
        <br/>
        <button onClick={askCSV}>Ask the CSV</button>
    </div>

    function increment(){
        props.model.setCounter(7);
    }

    function askCSV(){
        props.model.askCSV();
    }
}

export default ChildB;