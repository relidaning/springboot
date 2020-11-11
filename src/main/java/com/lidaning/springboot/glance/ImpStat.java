package com.lidaning.springboot.glance;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ImpStat {

    private int id;
    private Date impDate;
    private int impNum;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getImpDate() {
        return impDate;
    }

    public void setImpDate(Date impDate) {
        this.impDate = impDate;
    }

    public int getImpNum() {
        return impNum;
    }

    public void setImpNum(int impNum) {
        this.impNum = impNum;
    }
}
