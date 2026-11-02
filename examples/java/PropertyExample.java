// Apartments Property API – Java example (Java 11+)
// Usage: PILOTERR_API_KEY=your-key java PropertyExample.java
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class PropertyExample {
    static String enc(String v) {
        return URLEncoder.encode(v, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        String query = "query=" + enc("https://www.apartments.com/141-e-33rd-st-new-york-ny-unit-si-fl16-id43/p6b4mes/");

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.piloterr.com/v2/apartments/property?" + query))
            .header("x-api-key", System.getenv("PILOTERR_API_KEY"))
            .timeout(Duration.ofSeconds(120))
            .GET()
            .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
            .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.err.println("Piloterr error " + response.statusCode() + ": " + response.body());
            System.exit(1);
        }
        System.out.println(response.body());
    }
}
