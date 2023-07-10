
import java.util.Calendar;
import java.util.Random;




public class DFSearch_v2 {


    public static void main(String[] args) {
// TODO code application logic here
        long ts_b, ts_e;
//number of nodes
        int el_count=400;
        int thread_count = 8;
        int gCounter=0;
        int vertices[][] = new int[el_count][el_count]; // graph matrix
        boolean isVisited[] = new boolean[el_count];

        for(int i=0;i<el_count;i++){
            for(int j=0;j<el_count;j++){
                Random boolNumber = new Random();
                boolean edge = boolNumber.nextBoolean();
                vertices[i][j]=edge ? 1 : 0;

            }
        }
        DFSearch2 r[] = new DFSearch2[thread_count];
        ts_b = Calendar.getInstance().getTimeInMillis();
        for(int i = 0; i < thread_count; i++) {


            r[i] = new DFSearch2(el_count,vertices,isVisited,gCounter);
            r[i].start();

        }
        for(int i = 0; i < thread_count; i++) {

            try {

                r[i].join();

            } catch (InterruptedException e) {

            }

        }
        ts_e = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time "+(ts_e-ts_b));
    }
}
