package com.liuao.thread;

public class ThreadJoinPractice {
    public static void main(String[] args) throws InterruptedException {
        int n = 20;
        while(n-->0){
            System.out.println(Thread.currentThread().getName()+":"+"sayHi"+n);
            if(n==15){
                Thread thread = new Thread(new myThread1());
                thread.start();
                thread.join();
            }
        }
    }
}

class myThread1 implements Runnable{

    @Override
    public void run() {
        int n = 20;
        while(n-->0){
            System.out.println(Thread.currentThread().getName()+":"+"sayHello"+n);
            sleep();
        }
    }

    void sleep(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
