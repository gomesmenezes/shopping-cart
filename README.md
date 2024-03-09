# Shopping Cart

## Description

This is a Java shopping cart project. It allows users to add products to the cart, remove products, clear the cart, calculate the total cost of products in the cart, view the quantity of products in the cart, and view cart information.

## Features

- Add products to cart: Users can add products to the cart by providing the product name, price, and quantity.
- Remove products from cart: Users can remove products from the cart by providing the name of the product to be removed.
- Clear the cart: Users can clear all products from the cart at once.
- Calculate total cost: The system calculates the total cost of all products in the cart.
- View product quantity: Users can view the total quantity of products in the cart.
- View cart information: Users can view the names of the products in the cart.
- Insert products into the database: Products added to the cart can be inserted into a MySQL database.

## How it's Done

The project is structured into three main packages:

1. **Application**: Contains the main class `App` that handles user interface and interaction with the shopping cart.
2. **Entity**: Contains the entity classes `Product` and `ShoppingCart` representing products and the shopping cart, respectively. It also contains the `Util` class providing utility methods.
3. **Database**: Contains the `DataBaseConnection` class that manages the connection to the database and provides methods for inserting products into the database.

The project uses JDBC to connect to a MySQL database and insert products into the `product` table in the database.

## Running the Project

1. Make sure you have the Java Development Kit (JDK) and MySQL installed on your system.
2. Download and extract the project on your computer.
3. Open the project in your preferred Java IDE.
4. Make sure to add the MySQL JDBC driver to the project's classpath.
5. Run the `App` class to start the program.

## Contribution

Contributions are welcome! If you have any suggestions for improvement.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Author

- [@gomes.svg](https://www.linkedin.com/in/jos%C3%A9-gomes-de-menezes-7770aa270/)

