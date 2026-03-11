package com.liuao.thread;

public class ThreadTest2 {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            int count = 100;
            @Override
            public void run() {
                while (count>0){
                    System.out.println(Thread.currentThread().getName()+":"+count--);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

       Thread thread1 = new Thread(new Runnable() {
           int count=100;
           @Override
           public void run() {
               while (count>0){
                   System.out.println(Thread.currentThread().getName()+":"+count--);
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       });
       thread1.start();
    }
}
