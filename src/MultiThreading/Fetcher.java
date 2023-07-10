/**
 * @author gaurav.likhar
 * @date 08/07/23
 * @project_name Practice
 **/

package MultiThreading;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Fetcher {

    int maxUrls = 5;

    int maxRange = 10;

    Semaphore semaphore = new Semaphore(1);

    Random random = new Random();

    Map<String, List<String>> fetcherMap = new HashMap<>();

    public List<String> fetch(String url) throws InterruptedException {

        if(fetcherMap.containsKey(url)) return fetcherMap.get(url);
        semaphore.acquire();
        List<String> neighboursList = new ArrayList<>();
        int size = random.nextInt(5);
        for(int i=0; i<size; i++){
            int neighbour = random.nextInt(maxRange);
            neighboursList.add(String.valueOf(neighbour));
        }
        fetcherMap.put(url, neighboursList);
        semaphore.release();
        return neighboursList;

    }
}
