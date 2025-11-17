// import java.util.*;
import java.net.*;
// import java.time.*;
import java.io.*;
// import java.lang.*;

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
        this.writer.write("12345");
        this.writer.println();
    }

    public void disconnect() throws IOException {
        this.reader.close(); 
        this.writer.close();
        this.clientSocket.close();
    }

    public String request(String str) throws IOException {
        // this.writer.write(message);
        // this.writer.println();
        // String line = this.reader.readLine();
        int num = Integer.parseInt(str);
        int count = 0;
        for(int i = 1; i <= num; i++)
            if(num % i == 0) { count++; }

        return "The number " + num + " has " + count + " factors.\n";
    }

    public Socket getSocket() {
        return this.clientSocket ;
    }
}