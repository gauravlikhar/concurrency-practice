/**
 * @author gaurav.likhar
 * @date 09/07/23
 * @project_name Practice
 **/

package MultiThreading;

public class SingletonTestingClass {
    private volatile SingletonTestingClass singleton;

    public SingletonTestingClass() {
    }

    public SingletonTestingClass getInstance(){
        if(singleton==null){
            synchronized (this){
                if(singleton==null){
                    singleton = new SingletonTestingClass();
                }
            }
        }
        return singleton;
    }

}
