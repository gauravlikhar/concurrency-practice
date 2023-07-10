/**
 * @author gaurav.likhar
 * @date 08/07/23
 * @project_name Practice
 **/

package MultiThreading;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Crawler {

    Semaphore semaphore = new Semaphore(1);

    Random random = new Random();

    int n = 5;

    int maxDelay = 1000;

    public void crawl(Fetcher fetcher, Set<String> visited, String url, int level) throws InterruptedException {
        if(level>=5){
            System.out.println("Max level reached");
        }
        System.out.println("Crawling url " + url + " on thread " + Thread.currentThread().getName());
        semaphore.acquire();
        boolean alreadyVisited = visited.contains(url);
        visited.add(url);
        semaphore.release();
        if(alreadyVisited) return;

        List<Thread> threadList = new ArrayList<>();
        List<String> neighbours = fetcher.fetch(url);

        if(!neighbours.isEmpty()){
            System.out.print("neighbours of " + url + " are : ");
            for(String neighbour : neighbours)
                System.out.print(neighbour + " ");
            System.out.println();
        }

        for(String neighbour : neighbours){
            Thread t1 = new Thread(()-> {
                try{
                    Thread.sleep(random.nextInt(maxDelay));
                    crawl(fetcher, visited, neighbour, level+1);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            });
            threadList.add(t1);
            t1.start();
        }

        for(Thread thread : threadList){
            thread.join();
        }
    }
}
