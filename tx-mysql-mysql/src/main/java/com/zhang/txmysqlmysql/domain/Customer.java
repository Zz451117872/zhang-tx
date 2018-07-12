package com.zhang.txmysqlmysql.domain;

public class Customer {

    private Integer cid;
    private String name;
    private String password;
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
