// Customer Class
class Customer extends Thread {
    private final CoffeeShop coffeeShop;
    private final int customerNumber;
    private final String order;

    public Customer(CoffeeShop coffeeShop, int customerNumber, String order) {
        this.coffeeShop = coffeeShop;
        this.customerNumber = customerNumber;
        this.order = order;
    }

    @Override
    public void run() {
        try {
            coffeeShop.placeOrder(order, customerNumber);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
