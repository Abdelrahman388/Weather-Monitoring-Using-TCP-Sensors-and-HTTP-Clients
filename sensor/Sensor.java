import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;

public class Sensor {
    public static void main(String[] args) {
        try {
            System.out.println("Starting sensor...");
            Socket socket = new Socket("localhost", 9000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            out.println(scanner.nextLine());
            scanner.close();

            Scanner response = new Scanner(socket.getInputStream());
            System.out.println(response.nextLine());
            response.close();

            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
