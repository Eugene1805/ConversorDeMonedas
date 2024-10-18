package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        CurrencyConverter converter = new CurrencyConverter();
        String option;

        do {
            System.out.println("=====================================");
            System.out.println("    Bienvenido al conversor de monedas");
            System.out.println("=====================================");
            System.out.println();
            System.out.println("Seleccione una opción:");
            System.out.println("1) Dólar (USD) => Peso argentino (ARS)");
            System.out.println("2) Peso argentino (ARS) => Dólar (USD)");
            System.out.println("3) Dólar (USD) => Real brasileño (BRL)");
            System.out.println("4) Real brasileño (BRL) => Dólar (USD)");
            System.out.println("5) Dólar (USD) => Peso colombiano (COP)");
            System.out.println("6) Peso colombiano (COP) => Dólar (USD)");
            System.out.println("7) Salir");
            System.out.println();
            System.out.print("Elija una opción válida: ");
            option = sc.nextLine();

            if (!option.equals("7")) {
                System.out.print("Ingrese la cantidad a convertir: ");
                double amount = sc.nextDouble();
                sc.nextLine(); // Limpiar el buffer

                try {
                    double result = converter.convert(option, amount);
                    System.out.println("El resultado de la conversión es: " + result);
                } catch (Exception e) {
                    System.out.println("Error en la conversión: " + e.getMessage());
                }
            }

        } while (!option.equals("7"));

        System.out.println("Gracias por utilizar el conversor de monedas.");
    }
}
