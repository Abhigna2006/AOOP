package abc;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

// Producer class
class Producer implements Runnable {
    private BlockingQueue<String> sharedBuffer;
    private int messageCount;

    public Producer(BlockingQueue<String> sharedBuffer, int messageCount) {
        this.sharedBuffer = sharedBuffer;
        this.messageCount = messageCount;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= messageCount; i++) {
                String message = "Message " + i;
                System.out.println("Producer produced: " + message);
                sharedBuffer.put(message); // Synchronized insertion into the buffer
                TimeUnit.SECONDS.sleep(1); // Simulate some delay in producing messages
            }
            // End of message signal
            sharedBuffer.put("END");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Consumer class
class Consumer implements Runnable {
    private BlockingQueue<String> sharedBuffer;

    public Consumer(BlockingQueue<String> sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        try {
            String message;
            while (!(message = sharedBuffer.take()).equals("END")) { // Synchronized consumption from the buffer
                System.out.println("Consumer consumed: " + message);
                TimeUnit.SECONDS.sleep(2); // Simulate some delay in consuming messages
            }
            System.out.println("Consumer finished consuming all messages.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Main class to run the messaging application
public class MessagingApp {
    public static void main(String[] args) {
        BlockingQueue<String> sharedBuffer = new LinkedBlockingQueue<>();
        int messageCount = 5;

        // Create Producer and Consumer threads
        Thread producerThread = new Thread(new Producer(sharedBuffer, messageCount));
        Thread consumerThread = new Thread(new Consumer(sharedBuffer));

        // Start threads
        producerThread.start();
        consumerThread.start();
    }
}