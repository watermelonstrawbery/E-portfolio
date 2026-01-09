import ParentTest from './ParentTest';
import { Model } from './Model';

function GrandParent(){

    console.log("Render grandparent");
    
    const model = new Model()

    return <div>
        <h1>GrandParent</h1>
        <ParentTest model = {model}/>
    </div>
}

export default GrandParent;