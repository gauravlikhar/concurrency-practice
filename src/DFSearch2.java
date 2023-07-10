
import java.util.Stack;



public class DFSearch2 extends Thread{

    private boolean isVisit[];
    private final Stack<Integer> globalStack;
    int numberOfNodes;
    //traversal is done ?
    boolean isDone;
    int adj[][];
    // count visited nodes
    int gCounter;

    public DFSearch2(int number_Nodes,int adj[][],boolean isVisit[],int gCounter){
        this.numberOfNodes=number_Nodes;
        this.isVisit = isVisit;
        this.globalStack = new Stack<>();
        this.isDone=false;
        this.adj=adj;
        this.gCounter=gCounter;
        this.globalStack.push(number_Nodes-1);

    }

    public void run(){

        // local stack
        Stack<Integer> localStack = new Stack<>();
        while (!isDone) {
            int k;
            synchronized(globalStack){
                k = globalStack.pop();
                //pop until k is not visited
                while (isVisit[k]) {
                    if(globalStack.empty()) {
                        isDone=true;
                        return;
                    }else{
                        k=globalStack.pop();
                    }
                }

            }
            // traverse sub-graph with start node k
            DFSearchNode(localStack,k);
            yield();
            if(globalStack.empty()) {
                isDone = true;
            }
            // if gCounter is not null unvisited node are pushed in globalStack
            if(isDone&&gCounter<numberOfNodes){
                isDone=false;
                //unvisited nodes are pushed in globalStack
                for (int i = 0; i < isVisit.length; i++) {
                    if (!isVisit[i]) {
                        globalStack.push(i);
                    }

                }
            }


        }


    }
    synchronized private void DFSearchNode(Stack<Integer>   localStack, int k){

        localStack.push(k);

        while (!localStack.empty()) {
            int s=localStack.pop();
            if (!isVisit[s]) {
                isVisit[s] = true;
                gCounter++;
                //System.out.println("Node "+s+" is visit");
                //first element is pushed into localStack and anothers in   globalStack
                boolean flag = true; // local or global stack (true -> local; false ->global )
                for(int i=numberOfNodes-1; i>=0; i--)
                {
                    //
                    if(i==s) continue;
                    //push another successors in global stack
                    if(adj[s][i]==1&&!flag&&!isVisit[s]){//visited nodes are not pushed in globalStack
                        globalStack.push(i);
                    }
                    //push first successor  in global stack
                    if(adj[s][i]==1&&flag&&!isVisit[s]) //visited nodes are not pushed in localStack
                    {
                        localStack.push(i);
                        flag=false; //only first element is pushed into localStack
                    }

                }
            }


        }

    }

}
