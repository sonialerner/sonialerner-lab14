import java.net.*;
import java.io.*;

public class Client {

    Socket clientSocket = null ;
    BufferedReader reader = null;
    PrintWriter writer = null;

    Client(String host, int port) throws IOException {
        this.clientSocket = new Socket(host, port) ;
        this.reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.writer = new PrintWriter(this.clientSocket.getOutputStream(), true);
    }
    
    public void handshake() {
        this.writer.println("12345"); // send password to server
    }

    public void disconnect() throws IOException {
        this.reader.close();
        this.writer.close();
        this.clientSocket.close();
    }

    public String request(String str) throws IOException {
        this.writer.println(str); // send number to server
        return this.reader.readLine(); // return what server sends back
    }

    public Socket getSocket() {
        return this.clientSocket ;
    }
}