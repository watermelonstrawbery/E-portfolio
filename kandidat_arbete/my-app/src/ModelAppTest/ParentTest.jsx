import { observer } from 'mobx-react-lite';
import ChildA from './ChildA';
import ChildB from './ChildB';

const ParentTest = observer(({ model }) => {
  console.log("Parent renders with ");
  console.log(model);
  

  // If you read any observable here, this will track and re-render
  return (
    <div>
      <ChildA model={model} counter = {model.counter} csvText = {model.csvPromiseState.data}/>
      <ChildB model={model} />
    </div>
  );
});

export default ParentTest;