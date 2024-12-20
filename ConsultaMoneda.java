import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ConsultaMoneda {

    public Map<String, Double> cotizacionDe(String moneda) throws IOException, InterruptedException {

        String apikey = System.getenv("EXCHANGE_RATE_API_KEY");
        String urlString = "https://v6.exchangerate-api.com/v6/" + apikey + "/latest/" + moneda;
        URI direccion = URI.create(urlString);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.out.println("Error en la respuesta de la API. Código de estado: " + response.statusCode());
            return null;
        }

        String json = response.body();
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            if (rates.has("USD") && rates.has("BOB") && rates.has("ARS") && rates.has("BRL") && rates.has("CLP") && rates.has("COP")) {
                Map<String, Double> cotizaciones = new HashMap<>();
                cotizaciones.put("USD", rates.get("USD").getAsDouble());
                cotizaciones.put("ARS", rates.get("ARS").getAsDouble());
                cotizaciones.put("BOB", rates.get("BOB").getAsDouble());
                cotizaciones.put("COP", rates.get("COP").getAsDouble());
                cotizaciones.put("BRL", rates.get("BRL").getAsDouble());
                cotizaciones.put("CLP", rates.get("CLP").getAsDouble());
                cotizaciones.put("PEN", rates.get("PEN").getAsDouble());

                return cotizaciones;
            } else {
                System.out.println("Error: Algunas de las monedas no están disponibles en la respuesta.");
                return null;
            }
        } catch (Exception e){
            System.out.println("hubo un problema");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
