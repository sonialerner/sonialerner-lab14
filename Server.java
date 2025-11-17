import java.util.*;
import java.net.*;
import java.time.*;
import java.io.*;

public class Server {
    String host;
    int port;
    ServerSocket serverSocket = null ;
    Socket threadSocket = null ;
    BufferedReader reader = null;
    PrintWriter writer = null;
    ArrayList<LocalDateTime> connectedTimes = null;
    String passkey ;
    
    Server(int i) throws IOException{
        this.port = i ;
        this.reader = new BufferedReader(new InputStreamReader(System.in)) ;
        this.writer = new PrintWriter(this.threadSocket.getOutputStream(), true);
    }
    
    public void serve(int a) throws IOException {
        for(int i = 0; i < a; i++) {
            // process the client request in a separate thread
            // passkey check
            if(this.reader.readLine().equals(this.passkey)) { this.threadSocket = serverSocket.accept() ; }
            
            this.threadSocket.close() ;
        }
    }
    
    public ArrayList<LocalDateTime> getConnectedTimes() {
        return new ArrayList<>() ;
    }
    
    public void disconnect() {
        try {
            this.reader.close(); 
            this.writer.close();
            this.threadSocket.close();
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
