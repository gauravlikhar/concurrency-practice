import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author gaurav.likhar
 * @date 24/06/23
 * @project_name Practice
 **/

public class Hamming {
    public static int stringCollectionSize;
    public static int stringSize;
    public static List<String> stringCollection;
    public static String source;
    public static String target;
    public static List<List<Integer>> graph;

    private static List<Integer> distanceList;

    public static void main(String[] args) throws FileNotFoundException {
         File file = new File("/Users/gaurav.likhar/Documents/Personal/Practice/src/input");
         Scanner sc = new Scanner(file);

         stringCollectionSize = sc.nextInt();
         stringSize = sc.nextInt();
         stringCollection = new ArrayList<>(stringCollectionSize);

         for(int i = 0; i< stringCollectionSize; i++){
             stringCollection.add(sc.next());
         }
         source = sc.next();
         target = sc.next();

        graph = new ArrayList<>();
        int sourceIndex = 0;
        int destinationIndex = 0;
        for(int i = 0; i< stringCollectionSize; i++){
            graph.add(new ArrayList<>());
            if(stringCollection.get(i).equals(source)){
                sourceIndex = i;
            }
            if(stringCollection.get(i).equals(target)){
                destinationIndex = i;
            }
        }
         createGraph();
//        System.out.println("Source index : " + sourceIndex + ", destinationIndex : " + destinationIndex);
//        System.out.println(graph);
        bfs(sourceIndex);
        System.out.println(distanceList.get(destinationIndex));
    }

    private static void bfs(int root) {
        Queue<Integer> q = new LinkedList<>();
        distanceList = new ArrayList<>(stringCollectionSize);
        for(int i = 0; i< stringCollectionSize; i++){
            distanceList.add((int) 1e9);
        }
        q.add(root);
        distanceList.set(root, 0);
        while(!q.isEmpty()){
            int node= q.poll();
            List<Integer> temp = graph.get(node);
            for (Integer v : temp) {
                if (distanceList.get(v) > distanceList.get(node) + 1) {
                    distanceList.set(v, distanceList.get(node) + 1);
                    q.add(v);
                }
            }
        }
    }

    private static void createGraph() {
        for(int i = 0; i< stringCollectionSize; i++){
            for(int j = i+1; j < stringCollectionSize; j++){
                if(check(i,j)){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
    }

    private static boolean check(int x, int y){
        String s1 = stringCollection.get(x);
        String s2 = stringCollection.get(y);

        if(s1.length()!=s2.length()) return false;
        int count = 0;
        for(int i = 0; i< stringSize; i++){
            if(s1.charAt(i)!=s2.charAt(i))
                count++;
        }
        return count==1;
    }
}
