import java.util.*;
import java.net.*;
import java.time.*;
import java.io.*;

public class Client {

    String host ;
    int port ;
    Socket clientSocket ;

    Client(String host, int port) {
        this.host = host ;
        this.port = port ;
    }
    
    public void handshake() {

    }

    public void disconnect() {

    }

    public void close() {

    }

    public String request(String string) {

        return string ;
    }

    public Socket getSocket() {
        return this.clientSocket ;
    }
}
