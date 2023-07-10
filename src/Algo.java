import java.util.*;

/**
 * @author gaurav.likhar
 * @date 24/06/23
 * @project_name Practice
 **/

public class Algo {
    public static  int n, m;
    public static List<List<Integer>> g;

    public static boolean ans;
    public static int [] vis;
    public static void main(String[] args) {
        // File file = new File("/Users/gaurav.likhar/Documents/Personal/Practice/src/input");
        // Scanner sc = new Scanner(file);

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m= sc.nextInt();
        g = new ArrayList<>(n+1);
        for(int i=0; i<=n; i++){
            g.add(new ArrayList<>());
        }
        ans  = false;
        vis = new int[n+1];
        Arrays.fill(vis, 0);

        for(int i=0; i<m; i++){
            int x= sc.nextInt();
            int y= sc.nextInt();
            g.get(x).add(y);
            g.get(y).add(x);
        }

        dfs(1,0);

        if(ans){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
    }

    public static void dfs(int node, int parent){
        vis[node] = 1;
        List<Integer> arr = g.get(node);
        for(int i=0; i<arr.size(); i++){
            if(vis[arr.get(i)]==0){
                dfs(arr.get(i), node);
            }
            else if(arr.get(i)!=parent){
                ans = true;
            }
        }
    }

}
