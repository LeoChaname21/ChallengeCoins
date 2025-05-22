import com.google.gson.Gson;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner obj=new Scanner(System.in);
        ConsultaApi api=new ConsultaApi();

        while(true) {
            System.out.println("""
                    *****************************************
                    SEA BIENVENIDO/A AL CONVERSOR DE MONEDAS
                    
                    1) Dólar ==> Euro
                    2) Euro ==> Dólar
                    3) Dólar ==> Soles
                    4) Soles ==> Dólar
                    5) Dólar ==> Real brasileño
                    6) Real brasileño ==> Dólar
                    7) Salir
                    ESCOGE UNA OPCIÓN VÁLIDO
                    ****************************************""");
            int opcion;
            try {
                opcion = obj.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Escoje un número válido");
                obj.next();
                continue;
            }
            if (opcion == 7) {
                break;
            }
            if (opcion > 0 && opcion <= 7) {
                try {
                    Conversor conv = api.findTypeConversor("USD");
                    double usd = conv.conversion_rates().get("USD");
                    double op = 0.0;
                    String namecoin = "";
                    switch (opcion) {
                        case 1:
                            op = usd * conv.conversion_rates().get("EUR");
                            namecoin = "euros";
                            break;
                        case 2:
                            op = usd / conv.conversion_rates().get("EUR");
                            namecoin = "dólares";
                            break;
                        case 3:
                            op = usd * conv.conversion_rates().get("PEN");
                            namecoin = "soles";
                            break;
                        case 4:
                            op = usd / conv.conversion_rates().get("PEN");
                            namecoin = "dólares";
                            break;
                        case 5:
                            op = usd * conv.conversion_rates().get("BRL");
                            namecoin = "reales brasileños";
                            break;
                        case 6:
                            op = usd / conv.conversion_rates().get("BRL");
                            namecoin = "dólares";
                            break;
                        default:
                            System.out.println("No es una opcion correcta");
                    }

                    System.out.println("Escribe la cantidad a convertir: ");
                    try {
                        double cant = obj.nextDouble();
                        System.out.println("""
                                -----------------------------------------------
                                Son %.2f %s
                                ----------------------------------------------""".formatted(op * cant, namecoin));
                    }catch (InputMismatchException e){
                        System.out.println("ESCOGE UNA CANTIDAD VALIDA");
                        obj.next();
                        continue;
                    }
                }
                catch (Exception e){
                    System.out.println("Error al obtener los datos: "+e);
                    continue;
                }

                //Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
                //System.out.println(gson.toJson(conv));


                System.out.println("Desea continuar? Si/No");
                String crash = obj.next().toUpperCase();
                //System.out.println(crash);

                if (crash.equals("NO")) {
                    break;
                } else if (!crash.equals("SI")) {
                    System.out.println("Escribe una respuesta válida");

                }
            }
            else{
                System.out.println("PON UNA OPCION VÁLIDA!!!");
            }
        }

    }
}
