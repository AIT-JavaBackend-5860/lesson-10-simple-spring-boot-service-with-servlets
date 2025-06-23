package ait.cohort5860;

import ait.cohort5860.service.FixerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
public class CurrencyAppl {


    public static void main(String[] args) {
        SpringApplication.run(CurrencyAppl.class, args);

        Scanner scanner = new Scanner(System.in);
        FixerService fixer = new FixerService();

        Set<String> supportedCurrencies = Set.of(
                "USD", "EUR", "GBP", "JPY", "PLN", "CHF", "CAD", "AUD", "SEK", "NOK"
        );

        System.out.println("=== Currencies Converter via Fixer === \nAvailable currencies: " + supportedCurrencies);

        String from;
        while (true) {
            System.out.print("From currency: ");
            from = scanner.nextLine().toUpperCase();
            if (supportedCurrencies.contains(from)) break;
            System.out.println("Unsupported currency: " + from + ". Try again.");
        }


        String to;
        while (true) {
            System.out.print("To currency: ");
            to = scanner.nextLine().toUpperCase();
            if (supportedCurrencies.contains(to)) break;
            System.out.println("Unsupported currency: " + to + ". Try again.");
        }

        System.out.print("Amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid format");
            return;
        }


        double result = fixer.convert(from, to, amount);
        if (result != -1) {
            System.out.printf("Converted: %.2f %s = %.2f %s%n", amount, from, result, to);
        } else {
            System.out.println("Conversion failed (check network or API key).");
        }
    }

}
