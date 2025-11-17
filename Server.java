import java.util.*;
import java.net.*;
import java.time.*;
import java.io.*;

public class Server {
    ServerSocket serverSocket;
    ArrayList<LocalDateTime> connectedTimes;
    
    Server(int i) throws IOException { // initialize serverSocket and times AList
        this.serverSocket = new ServerSocket(i);
        this.connectedTimes = new ArrayList<>();
    }
    
    public void serve(int a) throws IOException {
        for(int i = 0; i < a; i++) { // for however many requests the caller indicated (via a)

            Socket s = this.serverSocket.accept(); // server waits for connection request and creates new socket upon request
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter writer = new PrintWriter(s.getOutputStream(), true);

            connectedTimes.add(LocalDateTime.now()); // keep track of when each connection happened

            String pwd = reader.readLine(); // get password from client

            if (!pwd.equals("12345")) { // handle incorrect password
                writer.println("couldn't handshake");
                s.close();
                reader.close();
                writer.close();
                continue; // we don't want rest of this method to execute since the client got the password wrong
            }

            // create and start new thread (so server can keep accepting new connection requests)
            ServerThread thread = new ServerThread(s);
            thread.start();
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
