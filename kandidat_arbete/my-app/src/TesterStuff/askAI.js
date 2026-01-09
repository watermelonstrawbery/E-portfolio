function askAI(prompt){
    const { GoogleGenerativeAI } = require("@google/generative-ai");
  
    const genAI = new GoogleGenerativeAI("AIzaSyDSsPu7iKIYT26cl25oImCmynkusxdfj4I");
    const model = genAI.getGenerativeModel({ model: "gemini-1.5-flash" });
  

    const aiPromise = model.generateContent(prompt);


    return aiPromise;

    //result.response.text()
}

export default askAI;