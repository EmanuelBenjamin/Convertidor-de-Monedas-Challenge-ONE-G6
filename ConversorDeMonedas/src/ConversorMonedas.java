import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class ConversorMonedas {
    private static final String API_KEY = "dda4094e2b908229424b6bac";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";

    public RespuestaAPI obtenerTasasCambio(String monedaOrigen) throws IOException, InterruptedException {
        String urlStr = API_URL + API_KEY + "/latest/" + monedaOrigen;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlStr))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        RespuestaAPI respuestaAPI = gson.fromJson(response.body(), RespuestaAPI.class);

        return respuestaAPI;
    }

    public double convertir(double cantidad, String monedaOrigen, String monedaDestino, Map<String, Double> tasas) {
        Double tasaOrigenObj = tasas.get(monedaOrigen);
        Double tasaDestinoObj = tasas.get(monedaDestino);

        if (tasaOrigenObj != null && tasaDestinoObj != null) {
            double tasaOrigen = tasaOrigenObj;
            double tasaDestino = tasaDestinoObj;
            return (cantidad / tasaOrigen) * tasaDestino;
        } else {
            System.out.println("Error: no se encontraron las tasas de cambio para las monedas seleccionadas.");
            return 0.0;
        }
    }
}

