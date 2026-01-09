function ChildA(props){
    console.log("childA renders with model");
    console.log(props.model);

    

    return <div>
        ChildA {props.counter} <br/>
        {props.csvText ? props.csvText.data[1] : "empty"}
    </div>
}

export default ChildA;