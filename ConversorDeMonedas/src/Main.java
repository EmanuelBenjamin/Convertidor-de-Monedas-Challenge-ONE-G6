import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConversorMonedas conversor = new ConversorMonedas();
        HistorialConversiones historial = new HistorialConversiones();
        Scanner scanner = new Scanner(System.in);



        // Mapa de códigos de moneda y países correspondientes
        Map<String, String> codigosMoneda = new HashMap<>();
        codigosMoneda.put("GTQ", "Quetzal          -GUATEMALA-");
        codigosMoneda.put("USD", "Dolar            -ESTADOS UNIDOS-");
        codigosMoneda.put("CAD", "Dolar Can.        -CANADA-");
        codigosMoneda.put("EUR", "Euro             -EUROPA-");
        codigosMoneda.put("MXN", "Peso Mexicano   -MEXICO-");
        codigosMoneda.put("BOB", "Boliviano        -BOLIVIA-");
        codigosMoneda.put("ARS", "Peso Argentino   -ARGENTINA-");
        codigosMoneda.put("COP", "Peso Colombiano  -COLOMBIA-");
        codigosMoneda.put("PEN", "Sol Peruano      -PERU-");
        codigosMoneda.put("BRL", "Real Brasileño   -BRASIL-");


        while (true) {

            System.out.println("****************** CONVERSOR DE MONEDAS ***************");
            System.out.println("************************** MENU ***********************");


            System.out.println("Seleccione una opcion: ");
            System.out.println("1. Convertir monedas");
            System.out.println("2. Ver historial de conversiones");
            System.out.println("3. Salir");



            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    // Mostrar el menú de códigos de moneda y países correspondientes
                    System.out.println("Códigos de moneda disponibles:");
                    System.out.println("|CODIGO|  MONEDA  |     PAIS   | ");
                    for (Map.Entry<String, String> entry : codigosMoneda.entrySet()) {
                        System.out.println(entry.getKey() + " - " + entry.getValue());
                    }
                    // Lógica para convertir monedas
                    System.out.println("Ingrese la cantidad a convertir: ");
                    double cantidad = scanner.nextDouble();
                    scanner.nextLine(); // Consumir el salto de línea

                    System.out.println("Ingrese el código de moneda de origen: ");
                    String monedaOrigen = scanner.nextLine();
                    monedaOrigen = monedaOrigen.toUpperCase();

                    System.out.println("Ingrese el código de la moneda a  convertir: ");
                    String monedaDestino = scanner.nextLine();
                    monedaDestino = monedaDestino.toUpperCase();
                    try {
                        RespuestaAPI respuesta = conversor.obtenerTasasCambio(monedaOrigen);
                        if (respuesta != null) {
                            double resultado = conversor.convertir(cantidad, monedaOrigen, monedaDestino, respuesta.getConversion_rates());
                            System.out.println();
                            System.out.println("Resultado: "+cantidad + " " + monedaOrigen + " es equivalente a : " + resultado + " " + monedaDestino);
                            System.out.println();
                            System.out.println("*******************************************************");
                            System.out.println("***LA CANTIDAD A SIDO CONVERTIDA A LA MONEDA DESEADA***");
                            System.out.println("*******************************************************");
                            System.out.println();

                            // Guardar La conversion en el historial
                            historial.agregarConversion(cantidad, monedaOrigen, monedaDestino, resultado);
                        }
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
                    }

                    break;
                case 2:
                    // Lógica para ver el historial de conversiones
                    List<HistorialConversiones.Conversion> conversions = historial.getConversiones();
                    if (conversions.isEmpty()){
                        System.out.println("No hay conversiones en el historial");
                    }else {
                        System.out.println("*************HISTORIAL DE CONVERSIONES*****************");
                        for (HistorialConversiones.Conversion conversion : conversions) {
                            System.out.println(conversion.getFechaHora() + ": " +
                                    conversion.getCantidad() + " " +
                                    conversion.getMonedaorigen() + " -> " +
                                    conversion.getResultado() + " " +
                                    conversion.getMonedaDestino());
                        }
                        System.out.println();
                    }

                    break;
                case 3:
                    System.out.println();
                    System.out.println("*******************************************************");
                    System.out.println("********************! HASTA LUEGO !********************");
                    System.out.println("*******************************************************");
                    System.out.println();
                    return;
                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");
            }

        }
    }
}
