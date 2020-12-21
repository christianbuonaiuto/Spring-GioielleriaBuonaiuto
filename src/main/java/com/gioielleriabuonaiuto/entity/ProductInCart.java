package com.gioielleriabuonaiuto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "productincart", schema = "public")
public class ProductInCart {

    public ProductInCart(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ido", nullable = false)
    private int ido;

    @ManyToOne
    @JoinColumn(name = "usero")
    private User usero;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @Basic
    @Column(name = "quantity", nullable = true)
    private Integer quantity;


    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public User getUsero() {
        return usero;
    }

    public void setUsero(User usero) {
        this.usero = usero;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInCart that = (ProductInCart) o;
        return ido == that.ido &&
                Objects.equals(usero, that.usero) &&
                Objects.equals(product, that.product) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ido, usero, product, quantity);
    }
}
