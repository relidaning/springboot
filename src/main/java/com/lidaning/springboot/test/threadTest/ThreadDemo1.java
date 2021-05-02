package com.lidaning.springboot.test.threadTest;

/**
 * 测试线程的等待与唤醒
 *
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        MyThread1 t = new MyThread1();
        Thread th = new Thread(t);
        th.start(); //开启这个线程
        //主线程
        synchronized (th) {
            for (int j = 0; j < 10; j++) {
                try {
                    Thread.sleep(1000); //主线程休眠1000毫秒
                    if (j == 9) {
                        t.awake();
                    }
                    System.out.println(Thread.currentThread().getName() + "___" + j);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}

class MyThread1 implements Runnable {
    private int j = 0;

    //线程执行完后获取j的值
    public int getJ() {
        return j;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000); //睡眠1000毫秒,让出cpu的时间片
                    System.out.println(Thread.currentThread().getName() + "___" + i);
                    if (i == 5) {
                        wait();//线程休眠
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void awake() {
        synchronized (this) {
            this.notify();
        }
    }
}