package tcpclient;
import java.net.*;
import java.io.*;

public class TCPClient {


    private final boolean shutdown;
    private final Integer timeout;
    private final Integer limit;


    public TCPClient(boolean shutdown, Integer timeout, Integer limit) {

        this.shutdown = shutdown;
        this.timeout = timeout;
        this.limit = limit;
    }


    public byte[] askServer(String hostname, int port, byte[] toServerBytes) throws IOException {

        byte[] webbdata = new byte[1024];
        ByteArrayOutputStream serverData = new ByteArrayOutputStream();
        Socket clientSocket = new Socket(hostname, port);

        clientSocket.getOutputStream().write(toServerBytes); //out to server
        if (timeout != null) {
            clientSocket.setSoTimeout(timeout);
        }

        if (shutdown) {
            System.out.println("Shutting down the outgoing connection...");
            clientSocket.shutdownOutput();
            System.out.println("Outgoing connection shutdown completed.");
        }

        try {
            int totalBytesRead = 0; //keeping track of the total number bytes received from the server
            //it is incremented as data is read

            while (true) {
                int BytesRead = clientSocket.getInputStream().read(webbdata);
                if (BytesRead == -1) {
                    break;
                }

                totalBytesRead += BytesRead;
                if (limit != null && totalBytesRead > limit) {
                    BytesRead -= (totalBytesRead - limit); // bytesRead gets trimmed if it exceeds the limit
                    serverData.write(webbdata, 0, BytesRead); //in from server
                    break;
                }
                serverData.write(webbdata, 0, BytesRead); //in from server
            }

        } catch (SocketTimeoutException e) {
            // Handle timeout exception here
            System.err.println(e);
        }

        clientSocket.close(); // Close the socket
        return serverData.toByteArray();

    }
}