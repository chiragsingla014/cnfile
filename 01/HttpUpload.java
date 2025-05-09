import java.io.*;
import java.net.*;

public class HttpUpload {
    public static void main(String[] args) throws IOException {
        
        File file = new File("capybara.jpg");
        Socket socket = new Socket("localhost", 8080);

        FileInputStream fileInputStream = new FileInputStream(file);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        dataOutputStream.writeLong(file.length());

        byte[] buffer = new byte[4096];
        int bytesRead;
        
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            dataOutputStream.write(buffer, 0, bytesRead);
        }

        fileInputStream.close();
        dataOutputStream.close();
        socket.close();

        System.out.println("Image uploaded successfully.");
    }
}
