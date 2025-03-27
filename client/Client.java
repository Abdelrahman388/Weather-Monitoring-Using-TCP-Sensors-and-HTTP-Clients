import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        System.out.println("Enter the City: ");
        Scanner sc = new Scanner(System.in);
        String city = sc.nextLine();
        sc.close();

        String url = "http://localhost:8080/weather/" + URLEncoder.encode(city, StandardCharsets.UTF_8);
        URI uri = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET().build();

        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
