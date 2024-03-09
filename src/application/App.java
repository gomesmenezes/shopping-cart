package application;

import database.DataBaseConnection;
import entity.Product;
import entity.ShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShoppingCart shoppingCart = new ShoppingCart();

        System.out.println("================================");
        System.out.println("   Welcome to The Gomes' Cart  ");
        System.out.println("================================");

        int option;

        do {
            displayMenu();
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                option = -1;
            }

            switch (option) {
                case 1:
                    addProduct(sc, shoppingCart);
                    break;
                case 2:
                    removeProduct(sc, shoppingCart);
                    break;
                case 3:
                    shoppingCart.clearShoppingCart(sc);
                    break;
                case 4:
                    shoppingCart.calculateCost();
                    break;
                case 5:
                    shoppingCart.getQuantity();
                    break;
                case 6:
                    shoppingCart.listProducts();
                    break;
                case 7:
                    insertProductsToDatabase(shoppingCart);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    if (option != -1) {
                        System.out.println("Invalid option. Please try again.");
                    }
            }
        } while (option != 0);

        DataBaseConnection.closeConnection();
        sc.close();
    }

    private static void displayMenu() {
        System.out.println("\nSwitch an option: ");
        System.out.println("1) Add product to cart");
        System.out.println("2) Remove product from cart");
        System.out.println("3) Clear all products from cart");
        System.out.println("4) Calculate total cost of products in cart");
        System.out.println("5) View quantity of products in cart");
        System.out.println("6) View information of cart");
        System.out.println("7) Insert products to database");
        System.out.println("0) Exit");
    }

    private static void addProduct(Scanner sc, ShoppingCart shoppingCart) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        System.out.println("Enter name of product ('exit' to cancel): ");
        String name = sc.nextLine();

        if (name.equalsIgnoreCase("exit")) {
            return;
        }

        System.out.println("Enter price of product: ");
        double price = getPriceFromUser(sc);

        System.out.println("Enter quantity of product: ");
        int quantity = getIntFromUser(sc);

        Product product = new Product(name, price);
        shoppingCart.addProduct(product, quantity);
        System.out.println("Product added to cart successfully.");
        dataBaseConnection.insertProduct(name, price, quantity);
    }

    private static void removeProduct(Scanner sc, ShoppingCart shoppingCart) {
        System.out.println("Enter name of product to remove: ");
        String name = sc.nextLine();

        if (shoppingCart.removeProductByName(name)) {
            System.out.println("Product removed from cart successfully.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    private static double getPriceFromUser(Scanner sc) {
        double price = 0;
        boolean validPrice = false;

        while (!validPrice) {
            try {
                price = Double.parseDouble(sc.nextLine().replace(",", "."));
                validPrice = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format. Please enter the price in the format 7,50");
            }
        }
        return price;
    }

    private static int getIntFromUser(Scanner sc) {
        int number = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                number = sc.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine();
            }
        }
        return number;
    }

    private static void insertProductsToDatabase(ShoppingCart shoppingCart) {
        for (Map.Entry<Product, Integer> entry : shoppingCart.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            DataBaseConnection.insertProduct(product.getName(), product.getPrice(), quantity);
        }
        System.out.println("Products inserted into database successfully.");
    }

}
