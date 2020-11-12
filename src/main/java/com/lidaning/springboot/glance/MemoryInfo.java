package com.lidaning.springboot.glance;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class MemoryInfo {

    private int id;
    private int wordId;
    private int repeatTimes;
    private Date repeatDate;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public int getRepeatTimes() {
        return repeatTimes;
    }

    public void setRepeatTimes(int repeatTimes) {
        this.repeatTimes = repeatTimes;
    }

    public Date getRepeatDate() {
        return repeatDate;
    }

    public void setRepeatDate(Date repeatDate) {
        this.repeatDate = repeatDate;
    }
}
