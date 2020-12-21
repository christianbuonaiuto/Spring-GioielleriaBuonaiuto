package com.gioielleriabuonaiuto.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "public")
public class Product {

    public Product(){

    }

    public Product(String code){
        this.code = code;
    }

    @Id
    @Column(name = "code", nullable = false, length = 7)
    private String code;

    @Basic
    @Column(name = "model", nullable = true, length = 50)
    private String model;

    @Basic
    @Column(name = "brand", nullable = true, length = 30)
    private String brand;

    @Basic
    @Column(name = "description", nullable = true, length = 300)
    private String description;

    @Basic
    @Column(name = "category", nullable = false)
    private String category;

    @Basic
    @Column(name = "quantity", nullable = true)
    private Integer quantity;

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    private Double price;

    @Basic
    @Column(name = "img", nullable = true)
    private String img;

    @Basic
    @Column(name="last_modify", nullable = false)
    private Date last_modify;

    @Version
    private long version;

    public Date getLastModify() {
        return last_modify;
    }

    public void setLastModify(Date lastModify) {
        this.last_modify = lastModify;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Product product = (Product) o;
        return Objects.equals(code, product.code) &&
                Objects.equals(model, product.model) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(description, product.description) &&
                Objects.equals(category, product.category) &&
                Objects.equals(quantity, product.quantity) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, model, brand, description, category, quantity, price);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String categoryByCategory) {
        this.category = categoryByCategory;
    }
}
