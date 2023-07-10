/**
 * @author gaurav.likhar
 * @date 08/07/23
 * @project_name Practice
 **/

package MultiThreading;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WebCrawler {

    public static void main(String[] args) throws InterruptedException {
        Fetcher fetcher = new Fetcher();
        Set<String> visited = new HashSet<>();

        Crawler crawler = new Crawler();
        crawler.crawl(fetcher, visited, "1", 1);

//        for(int i=1; i<=3; i++){
//            List<String> fetchedList =  fetcher.fetch(String.valueOf(i));
//            System.out.print(i + " : ");
//            for(String neighbour : fetchedList)
//                System.out.print(neighbour + " ");
//            System.out.println();
//        }
    }
}
