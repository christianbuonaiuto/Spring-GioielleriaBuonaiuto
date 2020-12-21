package com.gioielleriabuonaiuto.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ordero", schema = "public")
public class Order {

    public Order(){
    }

    public Order(String ido){
        this.ido = ido;
    }

    @Id
    @Column(name = "ido", nullable = false)
    private String ido;

    @ManyToOne
    @JoinColumn(name = "usero")
    private User usero;

    @Basic
    @Column(name = "date_purchase", nullable = true)
    private Date datePurchase;

    @Basic
    @Column(name = "amount", nullable = true, precision = 0)
    private Double amount;

    @Basic
    @Column(name = "comment")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIdo() {
        return ido;
    }

    public void setIdo(String ido) {
        this.ido = ido;
    }

     public User getUsero() {
        return usero;
    }

    public void setUsero(User usero) {
        this.usero = usero;
    }


    public Date getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Date datePurchase) {
        this.datePurchase = datePurchase;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return ido == order.ido &&
                Objects.equals(usero, order.usero) &&
                Objects.equals(datePurchase, order.datePurchase) &&
                Objects.equals(amount, order.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ido, usero, datePurchase, amount);
    }

}
