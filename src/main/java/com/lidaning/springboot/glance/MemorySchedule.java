package com.lidaning.springboot.glance;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MemorySchedule {

    private int id;
    private int first;
    private int second;
    private int third;
    private int fourth;
    private int fifth;
    private int sixth;
    private int seventh;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getThird() {
        return third;
    }

    public void setThird(int third) {
        this.third = third;
    }

    public int getFourth() {
        return fourth;
    }

    public void setFourth(int fourth) {
        this.fourth = fourth;
    }

    public int getFifth() {
        return fifth;
    }

    public void setFifth(int fifth) {
        this.fifth = fifth;
    }

    public int getSixth() {
        return sixth;
    }

    public void setSixth(int sixth) {
        this.sixth = sixth;
    }

    public int getSeventh() {
        return seventh;
    }

    public void setSeventh(int seventh) {
        this.seventh = seventh;
    }
}
