package com.lidaning.springboot.test.observertest;

/**
 * @author Administrator
 * @since 2021-5-6
 */
public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: "+ Integer.toBinaryString(subject.getState()));
    }
}
