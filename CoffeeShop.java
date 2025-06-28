import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CoffeeShop {
    private final Queue<String> orderQueue = new LinkedList<>();
    private final int capacity;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public CoffeeShop(int capacity) {
        this.capacity = capacity;
    }

    // Method for placing an order
    public void placeOrder(String order, int customerNumber) throws InterruptedException {
        lock.lock();
        try {
            while (orderQueue.size() == capacity) {
                System.out.println("Customer " + customerNumber + " is waiting to place order: " + order);
                notFull.await();
            }
            orderQueue.add(order);
            System.out.println("Customer " + customerNumber + " placed order: " + order);
            notEmpty.signalAll(); // Notify baristas that an order is available
        } finally {
            lock.unlock();
        }
    }

    // Method for preparing an order
    public String prepareOrder(int baristaNumber) throws InterruptedException {
        lock.lock();
        try {
            while (orderQueue.isEmpty()) {
                System.out.println("Barista " + baristaNumber + " is waiting for orders...");
                notEmpty.await();
            }
            String order = orderQueue.poll();
            System.out.println("Barista " + baristaNumber + " is preparing order: " + order);
            notFull.signalAll(); // Notify customers that space is available
            return order;
        } finally {
            lock.unlock();
        }
    }
}
