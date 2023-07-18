/**
 * @author gaurav.likhar
 * @date 14/07/23
 * @project_name Practice
 **/

package MultiThreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWriteLockWIthLockObject {

    private int reader;
    private int writer;
    private int writerRequests;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public ReadWriteLockWIthLockObject(int reader, int writer, int writerRequests) {
        this.reader = reader;
        this.writer = writer;
        this.writerRequests = writerRequests;
    }


    public void readLock() throws InterruptedException {
        lock.lock();
        if(writer>0 || writerRequests > 0){
            condition.await();
        }
        reader++;
        lock.unlock();
        notify();
    }

    public void readUnlock() throws InterruptedException {
        lock.lock();
        while (reader<=0){
            condition.await();
        }
        reader--;
        lock.unlock();
        notify();
    }

    public void writeLock() throws InterruptedException {
        lock.lock();
        writerRequests++;
        while(writer>0){
            condition.await();
        }
        writerRequests--;
        writer++;
        lock.unlock();
    }

    public void writeUnlock() throws InterruptedException {
        lock.lock();
        while(writer<=0){
            condition.await();
        }
        writer--;
        lock.unlock();
    }
}
