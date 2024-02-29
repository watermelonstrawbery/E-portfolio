package tcpclient;
import java.net.*;
import java.io.*;

public class TCPClient {
    
   
    public TCPClient() {
        
    }

    public byte[] askServer(String hostname, int port, byte [] toServerBytes) throws IOException {

        byte[] webdata = new byte[1024];
        ByteArrayOutputStream serverData = new ByteArrayOutputStream();
        int FromServerLength;
     

        Socket clientsocket = new Socket(hostname,port);
        clientsocket.getOutputStream().write(toServerBytes); //out to server 

        while((FromServerLength = clientsocket.getInputStream().read(webdata)) != -1)
                serverData.write(webdata, 0, FromServerLength); //in from server
                
        byte[] serverBytes = serverData.toByteArray();

        clientsocket.close();
        
        return serverBytes;
    }

}
