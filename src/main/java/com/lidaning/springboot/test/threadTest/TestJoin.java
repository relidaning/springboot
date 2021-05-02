package com.lidaning.springboot.test.threadTest;

public class TestJoin {

    public static void main(String[] args) throws InterruptedException {
        ThreadTest t1=new ThreadTest("A");
        ThreadTest t2=new ThreadTest("B");
        t1.start();
        t2.start();
        t2.join();
    }


}
class ThreadTest extends Thread {
    private String name;
    public ThreadTest(String name){
        this.name=name;
    }
    public void run(){
        for(int i=1;i<=50;i++){
            System.out.println(name+"-"+i);
        }
    }
}
