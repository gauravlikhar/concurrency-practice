/**
 * @author gaurav.likhar
 * @date 15/07/23
 * @project_name Practice
 **/

package MultiThreading;

public class ScheduledExecutor {


    public void timedExecute(Runnable runnable, long millis){
        new Thread(()-> {
           try{
                Thread.sleep(millis);
                runnable.run();
           }
           catch (InterruptedException ie){
               ie.printStackTrace();
           }
        }).start();
    }


    public static void main(String[] args) {
        ScheduledExecutor scheduledExecutor = new ScheduledExecutor();

        Runnable runnable1 = ()-> System.out.println("Hello 1");
        Runnable runnable2 = ()-> System.out.println("Hello 2");
        Runnable runnable3 = ()-> System.out.println("Hello 3");

        scheduledExecutor.timedExecute(runnable1, 1000);
        scheduledExecutor.timedExecute(runnable2, 2000);
        scheduledExecutor.timedExecute(runnable3, 500);
    }
}
