import java.util.*;
import java.net.*;
import java.time.*;
import java.io.*;

public class Server {
    // String host;
    // int port;
    ServerSocket serverSocket;
    // Socket threadSocket = null ;
    // BufferedReader reader = null;
    // PrintWriter writer = null;
    ArrayList<LocalDateTime> connectedTimes;
    // String passkey ;
    
    Server(int i) throws IOException {
        this.serverSocket = new ServerSocket(i);
        this.connectedTimes = new ArrayList<>();
        // this.reader = new BufferedReader(new InputStreamReader(System.in)) ;
        // this.writer = new PrintWriter(this.threadSocket.getOutputStream(), true);
    }
    
    public void serve(int a) throws IOException {
        for(int i = 0; i < a; i++) {

            Socket s = this.serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter writer = new PrintWriter(s.getOutputStream(), true);

            connectedTimes.add(LocalDateTime.now());

            String pwd = reader.readLine();

            if (!pwd.equals("12345")) {
                writer.println("couldn't handshake");
                s.close();
                reader.close();
                writer.close();
                continue;
            }

            ServerThread thread = new ServerThread(s);
            thread.start();

            // process the client request in a separate thread
            // passkey check
            // if(this.reader.readLine().equals(this.passkey)) { this.threadSocket = serverSocket.accept() ; }
            
            // this.threadSocket.close() ;
        }
    }
    
    public ArrayList<LocalDateTime> getConnectedTimes() {
        return new ArrayList<>() ;
    }
    
    public void disconnect() {
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
