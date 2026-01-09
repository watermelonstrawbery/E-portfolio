export function csvToObject(csvPromiseState, csvName){
    const Papa = require("papaparse");

    csvPromiseState.setPromise(fetch(csvName));

    csvPromiseState.promise.then(handleReceivedOuter);


    function handleReceivedOuter(receivedOuter){
        receivedOuter.text().then(handleReceivedInner)

        function handleReceivedInner(receivedInner){
            csvPromiseState.setData(Papa.parse(receivedInner));
        }
    }
}