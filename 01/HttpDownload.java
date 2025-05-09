import java.io.*;
import java.net.*;

public class HttpDownload {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server started, waiting for connection...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected.");

        DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
        FileOutputStream fileOutputStream = new FileOutputStream("received_image.jpg");

        long fileSize = dataInputStream.readLong();
        byte[] buffer = new byte[4096];
        int bytesRead;

        while (fileSize > 0 && (bytesRead = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, fileSize))) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
            fileSize -= bytesRead;
        }

        fileOutputStream.close();
        dataInputStream.close();
        clientSocket.close();
        serverSocket.close();

        System.out.println("Image received and saved as received_image.jpg.");
    }
}
