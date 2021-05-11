package com.lidaning.springboot.test.observertest;

/**
 * @author Administrator
 * @since 2021-5-6
 */
public class ObserverPatternDemo {

    public static void main(String[] args) {
        Subject subject = new Subject();
        new HexaObserver(subject);
        new BinaryObserver(subject);
        new OctalObserver(subject);
        System.out.println("added observers...");

        subject.setState(0);
    }
}
