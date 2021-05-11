package com.lidaning.springboot.test.observertest;

import java.util.ArrayList;
import java.util.List;

/**
 * 被订阅的主题
 */
public class Subject {

    //订阅的人员列表
    private List<Observer> observers = new ArrayList<Observer>();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        for(Observer observer:this.observers){
            observer.update();
        }
    }

    public void attach(Observer observer){
        observers.add(observer);
    }




}
