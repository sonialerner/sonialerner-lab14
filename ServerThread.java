import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread { // class for individual threads --- one for each request() call
    
    Socket clientSocket;
    BufferedReader reader;
    PrintWriter writer;

    public ServerThread(Socket s) throws IOException { // create new socket for each thread
        this.clientSocket = s;
        this.reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.writer = new PrintWriter(this.clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String str = reader.readLine(); // get number from client
            int num = 0;

            try {
                num = Integer.parseUnsignedInt(str); // input string --> integer
            } catch (NumberFormatException e) {
                writer.println("There was an exception on the server");
                clientSocket.close();
                reader.close();
                writer.close();
                return;
            }

            // calculate number of factors for given integer
            int count = 0;
            for(int j = 1; j <= num; j++)
                if(num % j == 0) { count++; }

            writer.println("The number " + num + " has " + count + " factors"); // write result to buffer

            // cleanup
            this.clientSocket.close();
            this.reader.close();
            this.writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
