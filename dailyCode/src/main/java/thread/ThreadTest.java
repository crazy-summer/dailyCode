package com.liuao.thread;

public class ThreadTest {
    public static void main(String[] args) {
        myThread myThread = new myThread();
        Thread thread = new Thread(myThread);
        thread.start();
    }
}

class myThread implements Runnable{
    int time = 100;
    @Override
    public void run(){
        while(true){
            System.out.println(Thread.currentThread().getName()+":爆炸倒计时"+time);
            time--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(time==0){
                return;
            }
        }
    }
}
