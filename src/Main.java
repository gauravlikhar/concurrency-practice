//import java.util.ArrayList;
//import java.util.List;
//import java.util.Stack;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//class Graph {
//    private int vertices;
//    private List<List<Integer>> adjacencyList;
//
//    public Graph(int vertices) {
//        this.vertices = vertices;
//        adjacencyList = new ArrayList<>(vertices);
//        for (int i = 0; i < vertices; i++) {
//            adjacencyList.add(new ArrayList<>());
//        }
//    }
//
//    public void addEdge(int source, int destination) {
//        adjacencyList.get(source).add(destination);
//    }
//
//    public void dfs(int startVertex) {
//        boolean[] visited = new boolean[vertices];
//        visited[startVertex] = true;
//        System.out.println("Visited: " + startVertex);
//
//        ExecutorService executor = Executors.newFixedThreadPool(vertices);
//
//        Stack<Integer> stack = new Stack<>();
//        stack.push(startVertex);
//
//        while (!stack.isEmpty()) {
//            int currentVertex = stack.peek();
//            int nextVertex = getNextUnvisitedNeighbor(currentVertex, visited);
//
//            if (nextVertex != -1) {
//                visited[nextVertex] = true;
//                System.out.println("Visited: " + nextVertex);
//                stack.push(nextVertex);
//            } else {
//                stack.pop();
//            }
//
//            if (stack.isEmpty() && !allVisited(visited)) {
//                int unvisitedVertex = getUnvisitedVertex(visited);
//                if (unvisitedVertex != -1) {
//                    visited[unvisitedVertex] = true;
//                    System.out.println("Visited: " + unvisitedVertex);
//                    stack.push(unvisitedVertex);
//                }
//            }
//
//            if (nextVertex != -1) {
//                Runnable worker = new DFSRunnable(nextVertex, visited.clone(), stack, this);
//                executor.execute(worker);
//            }
//        }
//
//        executor.shutdown();
//    }
//
//    private int getNextUnvisitedNeighbor(int vertex, boolean[] visited) {
//        for (int neighbor : adjacencyList.get(vertex)) {
//            if (!visited[neighbor]) {
//                return neighbor;
//            }
//        }
//        return -1;
//    }
//
//    private boolean allVisited(boolean[] visited) {
//        for (boolean v : visited) {
//            if (!v) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private int getUnvisitedVertex(boolean[] visited) {
//        for (int i = 0; i < vertices; i++) {
//            if (!visited[i]) {
//                return i;
//            }
//        }
//        return -1;
//    }
//}
//
//class DFSRunnable implements Runnable {
//    private int vertex;
//    private boolean[] visited;
//    private Stack<Integer> stack;
//    private Graph graph;
//
//    public DFSRunnable(int vertex, boolean[] visited, Stack<Integer> stack, Graph graph) {
//        this.vertex = vertex;
//        this.visited = visited;
//        this.stack = new Stack<>();
//        this.stack.addAll(stack);
//        this.graph = graph;
//    }
//
//    @Override
//    public void run() {
//        graph.dfs(vertex);
//    }
//}
//
//public class DFSThreadedExample {
//    public static void main(String[] args) {
//        Graph graph = new Graph(6);
//        graph.addEdge(0, 1);
//        graph.addEdge(0, 2);
//        graph.addEdge(1, 3);
//        graph.addEdge(1, 4);
//        graph.addEdge(2, 5);
//
//        int startVertex = 0;
//        graph.dfs(startVertex);
//    }
//}
