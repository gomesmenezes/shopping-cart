package entity;

import java.util.Scanner;

public class util {
    private double getPriceFromUser(Scanner sc) {
        double price = 0;
        boolean validPrice = false;

        while (!validPrice) {
            try {
                System.out.println("Enter price of product: ");
                price = sc.nextDouble();
                sc.nextLine(); // Consumir a quebra de linha pendente
                validPrice = true; // Se não lançar exceção, preço é válido
            } catch (Exception e) {
                System.out.println("Invalid price format. Please enter the price in the format 7,50");
                sc.nextLine(); // Limpar o buffer do scanner
            }
        }
        return price;
    }
}
