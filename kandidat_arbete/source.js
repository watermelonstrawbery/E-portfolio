async function doStuff(){
    const { GoogleGenerativeAI } = require("@google/generative-ai");

    const genAI = new GoogleGenerativeAI("AIzaSyDSsPu7iKIYT26cl25oImCmynkusxdfj4I");
    const model = genAI.getGenerativeModel({ model: "gemini-1.5-flash" });

    const prompt = "Explain how AI works";

    const result = await model.generateContent(prompt);
    console.log(result.response.text());





    var http = require('http');

    http.createServer(function (req, res) {
        res.writeHead(200, {'Content-Type': 'text/plain'});
        res.end('Hello World!');
    }).listen(8080);
}

doStuff();
