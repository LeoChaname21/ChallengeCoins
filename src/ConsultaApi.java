import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {

    public Conversor findTypeConversor(String coin){

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/8c79ba0d7715e4ba6ef46ae9/latest/"+coin);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion).build();

        try {
            HttpResponse<String> response = client
                    .send(request,HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Conversor.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
