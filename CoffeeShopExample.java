import java.util.Scanner;

// CoffeeShopExample Class (Main Method)
public class CoffeeShopExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get inputs from the user
        System.out.print("Enter the queue capacity: ");
        int queueCapacity = scanner.nextInt();

        System.out.print("Enter the number of customers: ");
        int numberOfCustomers = scanner.nextInt();

        System.out.print("Enter the number of baristas: ");
        int numberOfBaristas = scanner.nextInt();

        CoffeeShop coffeeShop = new CoffeeShop(queueCapacity);

        // Create and start barista threads
        for (int i = 1; i <= numberOfBaristas; i++) {
            new Barista(coffeeShop, i).start();
        }

        // Create and start customer threads
        for (int i = 1; i <= numberOfCustomers; i++) {
            new Customer(coffeeShop, i, "Order" + i).start();
        }

        scanner.close();
    }
}
