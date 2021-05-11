package com.lidaning.springboot.test.observertest;

/**
 * @author Administrator
 * @since 2021-5-6
 */
public class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal String: "+Integer.toOctalString(subject.getState()));
    }
}
