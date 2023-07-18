package MultiThreading;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadWriteLock {
    int reader;
    int writer;
    int writerRequests;

    public ReadWriteLock() {
        this.reader =0 ;
        this.writer = 0;
        this.writerRequests = 0;
    }

    public synchronized void readLock() throws InterruptedException{
        while(writer > 0 || writerRequests >0){
            wait();
        }
        reader++;
    }

    public synchronized void readUnlock() throws InterruptedException {
        while(reader==0){
            wait();
        }
        reader--;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException{
        writerRequests++;
        while(writer > 0){
            wait();
        }
        writerRequests--;
        writer++;
        notifyAll();
    }

    public synchronized void writeUnlock() throws InterruptedException {
        while(writer==0){
            wait();
        }
        writer--;
        notifyAll();
    }


    static class LRUCache extends ReadWriteLock{
        Map<Integer, Integer> mp;
        int size;

        public LRUCache(int size) {
            super();
            this.size = size;
            this.mp = new LinkedHashMap<>(size);
        }

        public int getValue(int key) throws InterruptedException {
            readLock();
            System.out.println("Acquired read lock");
            int ans  = mp.get(key);
            readUnlock();
            System.out.println("Released read lock");
            return ans;
        }

        public void setValue(int x) throws InterruptedException {
            writeLock();
            System.out.println("Acquired write lock");
            System.out.println("Map before put : {}"+ mp);
            if(mp.containsKey(x)){
                int temp = mp.remove(x);
                mp.put(x, temp+1);
            }
            else{
                if(mp.size() < size){
                    mp.put(x, 1);
                }
                else{
                    Integer firstKey = mp.keySet().stream().findFirst().get();
                    mp.remove(firstKey);
                    mp.put(x,1);
                }
            }
            System.out.println("Map after put : {}"+ mp);
            writeUnlock();
            System.out.println("Released write lock");
        }
    }

    private static class Multitasking implements Runnable{
        private LRUCache lruCache;
        private  int key;
        public Multitasking() {
        }

        public Multitasking(LRUCache lruCache, int key) {
            this.lruCache = lruCache;
            this.key = key;
        }

        public void run(){
            try {
                System.out.println(lruCache.getValue(key));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static class Multitasking2 implements Runnable{
        private LRUCache lruCache;
        private  int key;
        public Multitasking2() {
        }

        public Multitasking2(LRUCache lruCache, int key) {
            this.lruCache = lruCache;
            this.key = key;
        }

        public void run(){
            try {
                lruCache.setValue(key);
                System.out.println("Key set done");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LRUCache lruCache = new LRUCache(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable t1 = new Multitasking(lruCache, 1);
        Runnable t2 = new Multitasking(lruCache, 2);
        Runnable t3 = new Multitasking(lruCache, 3);



    }


}
