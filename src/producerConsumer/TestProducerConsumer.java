package producerConsumer;

import java.util.Arrays;

/**
 * Created by BSV on 18.01.2017.
 */
class TestProducerConsumer {

    public static void main(String[] args) {

        Buffer buffer = new Buffer();
        Consumer consumer = new TestProducerConsumer().new Consumer();
        Producer producer = new TestProducerConsumer().new Producer();

        Runnable consumerRunnable = () -> {
            for (int i = 0; i < 20; i++) {
                synchronized (buffer) {
                    if (buffer.bufferIsEmpty()) try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    consumer.consume(buffer.get());
                    buffer.notifyAll();
                }
            }
        };

        Runnable producerRunnable = () -> {
            for (int i = 0; i < 25; i++) {
                synchronized (buffer) {
                    if (buffer.bufferIsFull()) try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    buffer.add(producer.produce());
                    buffer.notifyAll();
                }
            }
        };

        Thread consumerThread = new Thread(consumerRunnable);
        Thread producerThread = new Thread(producerRunnable);

        producerThread.setName("ProducerThread");
        consumerThread.setName("ConsumerThread");

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(buffer.getBuffer()));

        // test that  buffer.getBuffer() method dont return link to original array of Buffer,
        // this method must return just copy of buffer.
        buffer.getBuffer()[0] = 111;
        System.out.println(Arrays.toString(buffer.getBuffer()));


    }

    private class Consumer {
        void consume(int a) {
            System.out.println("Consumer.Got " + a);
        }
    }

    private class Producer {
        int produce() {
            int z = (int) (Math.random() * 1000);
            System.out.println("Producer.produced - " + z);
            return z;
        }
    }

}


class Buffer {
    private int size = 10;
    private int[] buffer = new int[size];
    private int count = 0;                  // index of free element of array

    Buffer() {
    }

    Buffer(int size) {
        this.size = size;
    }

    boolean bufferIsEmpty() {
        return count == 0;
    }

    boolean bufferIsFull() {
        return count == size;
    }

    boolean add(int a) {
        if (count == size) return false;
        buffer[count] = a;
        count++;
        return true;
    }

    int get() {
        int z = buffer[count - 1];
        buffer[count - 1] = 0;
        count--;
        return z;
    }

    int[] getBuffer() {
        return Arrays.copyOf(buffer, size);
    }

}