package entity;

import database.DataBaseConnection;

import javax.xml.crypto.Data;
import java.sql.SQLOutput;
import java.util.*;

public class ShoppingCart {

    HashMap<Product, Integer> itens = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        itens.put(product, quantity);
    }

    public void removeProduct(Product product) {
        itens.remove(product);
    }

    public boolean removeProductByName(String productName) {
        for (Iterator<Map.Entry<Product, Integer>> iterator = itens.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Product, Integer> entry = iterator.next();
            Product product = entry.getKey();
            if (product.getName().equalsIgnoreCase(productName)) {
                iterator.remove();
                return true;
            }
        }
        return false; // Produto não encontrado no carrinho
    }

    public void clearShoppingCart(Scanner sc) {
        System.out.println("Have you confirm clear Shopping Cart? ('Y' for yes or 'N' for no)");
        String yesOrNo = sc.next();
        if (yesOrNo.equalsIgnoreCase("Y")) {
            itens.clear();
            System.out.println("Shopping cart Cleared successful");
        } else {
            System.out.println("Shopping cart not clear");
        }

    }

    public double calculateCost() {
        double totalCost = 0.0;
        for (Map.Entry<Product, Integer> entry : itens.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            double productCost = product.getPrice() * quantity;
            totalCost+= productCost;
        }
        System.out.println("=====VALUE-CART======");
        System.out.printf("All value of cart: $%.2f", totalCost);
        return totalCost;
    }

    public int getQuantity() {
        System.out.println("\n====QUANTITY=======");
        int quantityOutside = 0;
        for (Map.Entry<Product, Integer> entry : itens.entrySet()) {
            quantityOutside += entry.getValue();
        }
        if (quantityOutside == 0) {
            System.out.println("None quantity in product");
        } else {
            System.out.printf("Quantity: %d\n", quantityOutside);
        }
            return quantityOutside;
    }

    public void listProducts() {
        System.out.println("====LISTING====");
        for (Map.Entry<Product, Integer> entry : itens.entrySet()) {
            Product nameProduct = entry.getKey();
            System.out.print(nameProduct.getName() + " || ");
        }
        if (itens.isEmpty()) {
            System.out.println("Not products found");
        }
    }

    public void descriptionCart() {
        for (Map.Entry<Product, Integer> entry : itens.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            if (product == null && quantity == 0) {
                System.out.println("Not found products");
            } else {
                System.out.println("==============");
                System.out.printf("Product: %s \nPrice: $%.2f \nQuantity: %d\n", product.getName(), product.getPrice(), quantity);
            }

        }
    }

    public HashMap<Product, Integer> getProducts() {
        return itens;
    }

    public void insertProductsToDatabase() {
        for (Map.Entry<Product, Integer> entry : itens.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            DataBaseConnection.insertProduct(product.getName(), product.getPrice(), quantity);
        }
    }


}
