/**
 * @author gaurav.likhar
 * @date 09/07/23
 * @project_name Practice
 **/

package MultiThreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProducerConsumerWithLockObject {
    public static class BlockingQueue{
        Queue<Integer> queue;

        int maxSize;
        int currentSize = 0;

//        private final Object lock  = new Object();

        Semaphore semaphore = new Semaphore(1);

        public BlockingQueue(int maxSize) {
            this.maxSize = maxSize;
            this.queue = new LinkedList<>();
        }

        public void enqueue(int element) throws InterruptedException {
            semaphore.acquire();
            while (currentSize == maxSize) {
                System.out.println("Consumer please consume fast");
                semaphore.wait();
            }

            queue.add(element);
            currentSize++;
//            if(currentSize==maxSize)
//            semaphore.notify();
            semaphore.release();
        }

        public  int dequeue() throws InterruptedException {
            semaphore.acquire();
            while (currentSize == 0) {
                System.out.println("Producer please produce fast");
                semaphore.wait();
            }
            int ans = queue.poll();
            currentSize--;
//            semaphore.notify();
            semaphore.release();
            return ans;

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
//                    Thread.sleep(1000);
                    System.out.println("Producing element 2");
                    queue.enqueue(2);
//                    Thread.sleep(1000);
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
