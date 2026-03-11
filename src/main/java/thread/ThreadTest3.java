package thread;


import java.util.concurrent.*;

public class ThreadTest3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread1 myThread1 = new MyThread1();
        FutureTask<Object> futureTask = new FutureTask<Object>(myThread1);
        new Thread(futureTask).start();
        String o = (String) futureTask.get();
        System.out.println(o);
    }
}
class MyThread1 implements Callable{

    @Override
    public Object call() throws Exception {
        int count = 10;
        while(count>0){
            System.out.println(Thread.currentThread().getName()+count);
            count--;
            Thread.sleep(100);
        }
        return "success";
    }
}
