import java.util.Map;
public record Conversor(String result,
                        String documentation,
                        String terms_of_use,
                        String base_code,
                        Map<String,Double> conversion_rates) {

}
