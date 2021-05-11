package com.lidaning.springboot.test.observertest;

/**
 * @author Administrator
 * @since 2021-5-6
 */
public class HexaObserver extends Observer{

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hexa String: "+Integer.toHexString(subject.getState()));
    }
}
