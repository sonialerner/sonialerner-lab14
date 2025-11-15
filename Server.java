import java.util.*;
import java.net.*;
import java.time.*;
import java.io.*;

public class Server {
    private int port ;
    private static final int passkey = 12345 ;

    Server(int port) {
        this.port = port ;
    }

    public void serve(int a) {

        for(int i = 0; i < a; i++) {
            // process the client request in a separate thread
        }

    }

    public ArrayList<LocalDateTime> getConnectedTimes() {
        return new ArrayList<>() ;
    }

    public void disconnect() {

    }
}
