package com.lidaning.springboot.test.observertest;

/**
 * 观察者接口
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
