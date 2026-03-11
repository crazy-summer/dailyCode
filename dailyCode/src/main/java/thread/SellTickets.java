package com.liuao.thread;

public class SellTickets {
    public static void main(String[] args) {
// 方式一：继承thread类
//        Sell1 sell1 = new Sell1();
//        Sell1 sell2 = new Sell1();
//        Sell1 sell3 = new Sell1();
//        sell1.start();
//        sell2.start();
//        sell3.start();
 // 方式二：实现runable接口
        Sell2 sell1 = new Sell2();
        Thread thread1 = new Thread(sell1);
        Thread thread2 = new Thread(sell1);
        Thread thread3 = new Thread(sell1);
        thread1.start();
        thread2.start();
        thread3.start();

    }


}
class Sell1 extends Thread{
    static int tickets = 100;
    public synchronized void m(){

    }
    @Override
    public void run(){
        m();
    }
}

class Sell2 implements Runnable{
    static int tickets = 100;

    @Override
    public synchronized void run(){
        while (true){
            System.out.println(Thread.currentThread().getName()+"当前票数为："+tickets);
            tickets--;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(tickets<=0){
                break;
            }
        }
    }
}

