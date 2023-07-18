/**
 * @author gaurav.likhar
 * @date 16/07/23
 * @project_name Practice
 **/

package MultiThreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barrier {

    private int partiesAwait=0;

    private int count=0;

    private int partiesCompleted=0;

    private int released=0;
    private int maxCount;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public Barrier(int maxCount) {
        this.maxCount = maxCount;
    }


    public void await() throws InterruptedException{
        lock.lock();
        while(count==maxCount){
            condition.await();
        }
        count++;

        while(count < maxCount){
            condition.await();
        }
        partiesCompleted++;
        release1();
        condition.signalAll();

        if(partiesCompleted==maxCount) {
            count=0;
            partiesCompleted=0;
            condition.signalAll();
        }
        lock.unlock();
    }

    private void release1() {
        System.out.println("releasing the thread " + Thread.currentThread().getName());
    }
}
