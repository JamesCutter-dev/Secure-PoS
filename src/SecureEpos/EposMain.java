package SecureEpos;

import SecureEpos.Cart.Cart;
import SecureEpos.Product.Product;
import SecureEpos.Product.ProductManager;

import java.util.Scanner;

public class EposMain { // This class is used to manage the EPoS system
    private static ProductManager productManager = new ProductManager();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu(); // this allows the user to select the menu options
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> viewProducts();
                case 3 -> updateProduct();
                case 4 -> removeProduct();
                case 5 -> addItemToCart();
                case 6 -> viewCart();
                case 7 -> applyDiscount();
                case 8 -> processPayment();
                case 9 -> checkout();
                case 10 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void showMenu() { // this method displays the menu options to the user
        System.out.println("\n--- EPoS System ---");
        System.out.println("1. Add product");
        System.out.println("2. View products");
        System.out.println("3. Update product");
        System.out.println("4. Remove product");
        System.out.println("5. Add item to cart");
        System.out.println("6. View cart");
        System.out.println("7. Apply discount");
        System.out.println("8. Process payment");
        System.out.println("9. Checkout");
        System.out.println("10. Exit");
        System.out.print("Enter choice: ");
    }

    private static void addProduct() { // this method allows the user to add a product, 1.2 updated to provide better input validation
        System.out.print("Enter product ID (must be a 4 digit integer: ");
        while (!scanner.hasNextInt()) { // new input validation
            System.out.println("Invalid input. Please enter a 4 digit integer.");
            scanner.next();
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        if (id < 1000 || id > 9999) {
            System.out.println("Invalid input. Please enter a 4 digit number.");
            return;
        }
        System.out.print("Enter product name: ");
        String name = scanner.nextLine(); // Ensure this captures the entire line of text

        System.out.print("Enter product price (Must be positive): ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid price.");
            scanner.next();
        }
        double price = scanner.nextDouble();
        if (price < 0) {
            System.out.println("Invalid input. Please enter a valid price.");
            return;
        }

        System.out.print("Enter product quantity (non-negative integer): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid quantity.");
            scanner.next();
        }
        int quantity = scanner.nextInt();
        if (quantity < 0) {
            System.out.println("Invalid input. Please enter a valid quantity.");
            return;
        }

        System.out.print("Enter product discount (Cannot be negative): ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid discount.");
            scanner.next();
        }
        double discount = scanner.nextDouble();
        if (discount < 0) {
            System.out.println("Invalid input. Please enter a valid discount.");
            return;
        }
        Product product = new Product(id, name, price, quantity);
        product.setDiscount(discount);
        productManager.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private static void viewProducts()  { // this method allows the user to view the products
        productManager.viewProducts();
    }

    private static void updateProduct() { // this method allows the user to update a product
        System.out.print("Enter product ID tp change(must be a 4 digit integer: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid product.");
            scanner.next();
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        if (id < 1000 || id > 9999) {
            System.out.println("Invalid input. Please enter a valid product ID.");
            return;
        }

        System.out.println("Enter new product name: ");
        String name = scanner.nextLine();

        System.out.println("Enter new product price: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid price.");
            scanner.next();
        }
        double price = scanner.nextDouble();
        if (price < 0) {
            System.out.println("Invalid input. Please enter a valid price.");
            return;
        }

        System.out.println("Enter new product quantity: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid quantity.");
            scanner.next();
        }
        int quantity = scanner.nextInt();
        if (quantity < 0) {
            System.out.println("Invalid input. Please enter a valid quantity.");
            return;
        }

        System.out.println("Enter new product discount: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid discount.");
            scanner.next();
        }
        double discount = scanner.nextDouble();
        if (discount < 0) {
            System.out.println("Invalid input. Please enter a valid discount.");
            return;
        }
        productManager.updateProduct(id, name, price, quantity, discount);
    }

    private static void removeProduct() { // this method allows the user to remove a product
        System.out.print("Enter product ID to remove: ");
        int id = scanner.nextInt();
        productManager.removeProduct(id);
    }

    private static void addItemToCart() { // this method allows the user to add an item to the cart
        System.out.println("Enter product ID to add to cart: ");
        int id = scanner.nextInt();
        Product product = productManager.getProductById(id);
        if (product != null) {
            System.out.println("Enter quantity: ");
            int quantity = scanner.nextInt();
            if (quantity <= product.getQuantity()) {
                cart.addItem(product, quantity);
                product.setQuantity(product.getQuantity() - quantity);
                System.out.println("Item added to cart.");
            } else {
                System.out.println("Insufficient stock.");
            }
        }else {
                System.out.println("Product not found.");
        }
    }

    private static void viewCart() { // this method allows the user to view the cart
        cart.viewCart();
    }

    private static void applyDiscount() {
        System.out.println("Enter discount percentage: ");
        double discount = scanner.nextDouble();
        cart.applyDiscount(discount);
        System.out.println("Discount applied.");
    }

    private static void processPayment() { // this method allows the user to process the payment
        System.out.println("Enter payment amount: ");
        double amountPaid = scanner.nextDouble();
        double change = cart.processPayment(amountPaid);
        if (change >= 0) {
            System.out.println("Change: " + change);
        } else {
            System.out.println("Insufficient payment.Please try again");
        }
    }

    private static void checkout() { // this method allows the user tocheckout
        cart.checkout();
        System.out.println("Thank you for shopping with us.");
    }
}
