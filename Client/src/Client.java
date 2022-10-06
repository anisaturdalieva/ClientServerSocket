import turdalieva.Phone;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (
             Phone phone = new Phone("127.0.0.1", 8000))  {
            System.out.println ( "Connected to Server" );
            String request = "Visaginas";
            System.out.println ("Request: "+request);
            phone.writeLine(request);
            String response = phone.readerLine ();
            System.out.println ("Response: " + response);

        }catch (IOException e){
            e.printStackTrace ();
        }
    }
}
