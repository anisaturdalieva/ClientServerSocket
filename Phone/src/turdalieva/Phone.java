package turdalieva;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Phone implements Closeable{

    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;


    public Phone(String ip, int port){
        try {
            this.socket = new Socket (ip,port);
            this.reader = creatReader();
            this.writer = creatWriter();
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
    }
    public Phone(ServerSocket server){
       try {
           this.socket = server.accept ();
           this.reader = creatReader ();
           this.writer = creatWriter ();
       } catch (IOException e) {
           throw new RuntimeException ();
       }
   }
   public void writeLine(String massage){
        try {
            writer.write ( massage );
            writer.newLine ();
            writer.flush ();
        }catch (IOException e){
            throw new RuntimeException ();
        }
   }
   public String readerLine(){
        try {
            return reader.readLine ();
        }catch (IOException e) {
            throw new RuntimeException ( e );
        }
   }

    private BufferedReader creatReader(){
        try {
            return new BufferedReader ( new InputStreamReader ( socket.getInputStream () ) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private BufferedWriter creatWriter(){
        try {
            return new BufferedWriter ( new OutputStreamWriter ( socket.getOutputStream () ) );
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
    }

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     *
     * <p> As noted in {@link AutoCloseable#close()}, cases where the
     * close may fail require careful attention. It is strongly advised
     * to relinquish the underlying resources and to internally
     * <em>mark</em> the {@code Closeable} as closed, prior to throwing
     * the {@code IOException}.
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        socket.getInputStream ().close ();
        writer.close ();
        reader.close ();
        socket.close ();
    }
}
