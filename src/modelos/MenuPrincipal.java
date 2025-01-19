package conversor.modelos;
import com.google.gson.Gson;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DecimalFormat;

public class MenuPrincipal {

    private final conversor.modelos.Connection connection;
    private final Gson gson;

    private TasaConversor tasaCambio;

    public void mainMenu() {
        Scanner scn = new Scanner(System.in);
        int input = 0;
        boolean validInput;
        do {
            System.out.println("-----------------------------");
            System.out.println("Bienvenido al conversor de moneda [$̲̅(̲̲̅̅̅)̲̅$̲̅]");
            System.out.println("1. USD a MXN");
            System.out.println("2. MXN a USD");
            System.out.println("3. EUR a USD");
            System.out.println("4. USD a EUR");
            System.out.println("5. CNY a USD");
            System.out.println("6. USD a CNY");
            System.out.println("7. BRL a USD");
            System.out.println("8. USD a BRL");
            System.out.println("9. ARS a USD");
            System.out.println("10. USD a ARS");
            System.out.println("11. Salir.");
            System.out.println("-----------------------------");
            validInput = false;
            while (!validInput) {
                System.out.print("Selecciona una opción del menú: \n");
                try {
                    input = scn.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Por favor selecciona una opción válida del menú.");
                    scn.next();
                }
            }

            switch (input) {
                case 1:
                    this.tasaCambio = llamarTipoMoneda("USD", "MXN");
                    hacerConversion(scn, "USD", "MXN");
                    break;
                case 2:
                    this.tasaCambio = llamarTipoMoneda("MXN", "USD");
                    hacerConversion(scn, "MXN", "USD");
                    break;
                case 3:
                    this.tasaCambio = llamarTipoMoneda("EUR", "USD");
                    hacerConversion(scn, "EUR", "USD");
                    break;
                case 4:
                    this.tasaCambio = llamarTipoMoneda("USD", "EUR");
                    hacerConversion(scn, "USD", "EUR");
                    break;
                case 5:
                    this.tasaCambio = llamarTipoMoneda("CNY", "USD");
                    hacerConversion(scn, "CNY", "USD");
                    break;
                case 6:
                    this.tasaCambio = llamarTipoMoneda("USD", "CNY");
                    hacerConversion(scn, "USD", "CNY");
                    break;
                case 7:
                    this.tasaCambio = llamarTipoMoneda("BRL", "USD");
                    hacerConversion(scn, "BRL", "USD");
                    break;
                case 8:
                    this.tasaCambio = llamarTipoMoneda("USD", "BRL");
                    hacerConversion(scn, "USD", "BRL");
                    break;
                case 9:
                    this.tasaCambio = llamarTipoMoneda("ARS", "USD");
                    hacerConversion(scn, "ARS", "USD");
                    break;
                case 10:
                    this.tasaCambio = llamarTipoMoneda("USD", "ARS");
                    hacerConversion(scn, "USD", "ARS");
                    break;
            }

        } while (input != 11);
    }

    public MenuPrincipal(Connection connection, Gson gson) {
        this.connection = connection;
        this.gson = gson;
    }

    private TasaConversor llamarTipoMoneda(String monedaOrigen, String monedaDestino) {
        String json = connection.getConnection(
                "https://v6.exchangerate-api.com/v6/1e1b8985edbafdc55b7d0360/pair/" + monedaOrigen + "/" + monedaDestino);
        return gson.fromJson(json, TasaConversor.class);
    }

    private void hacerConversion(Scanner scn, String monedaOrigen, String monedaDestino) {
        System.out.println("-----------------------------");
        System.out.println("La tasa de cambio es: " + this.tasaCambio.tasaCambioToString());
        System.out.println("-----------------------------");
        System.out.println("Indique la cantidad que desea convertir (" + monedaOrigen + " a " + monedaDestino + ")");
        double amount = 0;
        try {
            amount = scn.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Monto inválido");
            scn.next();
        }
        System.out.println("⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢");

        DecimalFormat formatoNumeros = new DecimalFormat("#,###.00");

        System.out.println(
                formatoNumeros.format(amount) + " " + this.tasaCambio.monedaOrigen()
                        + " es equivalente a "
                        + formatoNumeros.format(calculator(amount)) + " " + this.tasaCambio.monedaDestino());
        System.out.println("⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢⟢");
    }

    private double calculator(double amount) {
        return amount * this.tasaCambio.tasaCambio();
    }
}
