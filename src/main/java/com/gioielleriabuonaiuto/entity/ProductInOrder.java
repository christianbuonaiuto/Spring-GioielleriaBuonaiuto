package com.gioielleriabuonaiuto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "productinorder", schema = "public")
public class ProductInOrder {

    public ProductInOrder(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ido")
    private int ido;

    @ManyToOne
    @JoinColumn(name = "ordero")
    private Order ordero;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @Basic
    @Column(name = "quantity", nullable = true)
    private Integer quantity;

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    private Double price;


    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public Order getOrdero() {
        return ordero;
    }

    public void setOrdero(Order ordero) {
        this.ordero = ordero;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrder that = (ProductInOrder) o;
        return ido == that.ido &&
                Objects.equals(ordero, that.ordero) &&
                Objects.equals(product, that.product) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ido, ordero, product, quantity, price);
    }
}
