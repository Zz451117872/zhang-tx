package com.zhang.txmysqlmysql.domain;

public class Order {

    private Integer oid;
    private Integer cid;
    private String title;
    private Integer amount;

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", cid=" + cid +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                '}';
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
