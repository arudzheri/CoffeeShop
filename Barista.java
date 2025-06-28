// Barista Class
class Barista extends Thread {
    private final CoffeeShop coffeeShop;
    private final int baristaNumber;

    public Barista(CoffeeShop coffeeShop, int baristaNumber) {
        this.coffeeShop = coffeeShop;
        this.baristaNumber = baristaNumber;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String order = coffeeShop.prepareOrder(baristaNumber);
                System.out.println("Barista " + baristaNumber + " completed order: " + order);
                Thread.sleep(1000); // Simulate time taken to prepare coffee
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
