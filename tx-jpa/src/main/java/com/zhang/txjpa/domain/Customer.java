package com.zhang.txjpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "customer1")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer cid;

    @Column(name = "name" ,unique = true)
    private String name;

    @Column
    private String password;

    @Column
    private Integer amount;


    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", amount=" + amount +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

