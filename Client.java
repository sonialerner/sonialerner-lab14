// import java.util.*;
import java.net.*;
// import java.time.*;
import java.io.*;

public class Client {

    String host;
    int port;
    Socket clientSocket = null ;
    BufferedReader reader = null;
    PrintWriter writer = null;

    Client(String host, int port) throws IOException {
        this.clientSocket = new Socket(host, port) ;
        this.reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.writer = new PrintWriter(this.clientSocket.getOutputStream(), true);
    }
    
    public void handshake() {
        this.writer.write("12345");
        this.writer.println();
    }

    public void disconnect() throws IOException {
        this.reader.close(); 
        this.writer.close();
        this.clientSocket.close();
    }

    public String request(String string) throws IOException {
        String message = string;
        this.writer.write(message);
        this.writer.println();
        String line = "";

        line = this.reader.readLine();

        return line ;
    }

    public Socket getSocket() {
        return this.clientSocket ;
    }
}
