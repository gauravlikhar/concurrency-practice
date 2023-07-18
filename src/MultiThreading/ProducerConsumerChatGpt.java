/**
 * @author gaurav.likhar
 * @date 18/07/23
 * @project_name Practice
 **/

package MultiThreading;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerChatGpt {
    public static class BlockingQueue<T> {
        private final Queue<T> queue;
        private final int capacity;
        private final Lock lock;
        private final Condition notEmpty;
        private final Condition notFull;

        public BlockingQueue(int capacity) {
            this.capacity = capacity;
            this.queue = new LinkedList<>();
            this.lock = new ReentrantLock();
            this.notEmpty = lock.newCondition();
            this.notFull = lock.newCondition();
        }

        public void enqueue(T item) throws InterruptedException {
            lock.lock();
            try {
                while (queue.size() == capacity) {
                    // Queue is full, wait for space to become available
                    notFull.await();
                }
                queue.offer(item);
                // Signal that the queue is not empty anymore
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        public T dequeue() throws InterruptedException {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    // Queue is empty, wait for an item to be added
                    notEmpty.await();
                }
                T item = queue.poll();
                // Signal that the queue is not full anymore
                notFull.signal();
                return item;
            } finally {
                lock.unlock();
            }
        }

        public int size() {
            lock.lock();
            try {
                return queue.size();
            } finally {
                lock.unlock();
            }
        }
    }


    public static class Producer implements Runnable{

        BlockingQueue queue;

        public Producer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("Producing element 1");
                    queue.enqueue(1);
                    Thread.sleep(1000);
                    System.out.println("Producing element 2");
                    queue.enqueue(2);
                    Thread.sleep(1000);
                    System.out.println("Producing element 3");
                    queue.enqueue(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static class Consumer implements Runnable{
        BlockingQueue queue;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true){
                try {
                    System.out.println("Consumed "+ queue.dequeue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new BlockingQueue(2);
        Runnable Producer = new Producer(blockingQueue);
        Runnable Consumer = new Consumer(blockingQueue);
        Thread t1  = new Thread(Producer);
        Thread t2 = new Thread(Consumer);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}
