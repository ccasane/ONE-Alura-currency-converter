import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        ConsultaMoneda conversion = new ConsultaMoneda();
        var lectura = new Scanner(System.in);
        String monedaInicial = "";
        String monedaFinal;
        double valor;
        double valorConvertido;

        while (!"SALIR".equals(monedaInicial)) {
            System.out.println("-----------------------------");
            System.out.println("¡Bienvenid(a) al \"Conversor de Monedas\"!");
            System.out.println("Monedas posibles a convertir:");
            System.out.println("USD");
            System.out.println("ARS");
            System.out.println("COP");
            System.out.println("BOB");
            System.out.println("BRL");
            System.out.println("CLP");
            System.out.println("PEN");
            System.out.println("Ingrese \"SALIR\" para terminar la ejecución.");
            System.out.println("Ingrese de qué moneda quiere partir la conversión: ");
            monedaInicial = lectura.nextLine().toUpperCase();
            if ("SALIR".equals(monedaInicial)) { break; }
            System.out.println("Ingrese a qué moneda quiere convertir sus " + monedaInicial);
            monedaFinal = lectura.nextLine().toUpperCase();
            System.out.println("Ingrese el valor a convertir: ");
            valor = lectura.nextDouble();
            lectura.nextLine();

            try {
                var consulta = conversion.cotizacionDe(monedaInicial);

                valorConvertido = valor * consulta.get(monedaFinal);

                System.out.println("Sus " + valor + " " + monedaInicial + " equivalen a " + valorConvertido + " " + monedaFinal);
                System.out.println("**********************************");

            } catch (NullPointerException e) {
                System.out.println("Una de las monedas no es válida.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
    }
}
