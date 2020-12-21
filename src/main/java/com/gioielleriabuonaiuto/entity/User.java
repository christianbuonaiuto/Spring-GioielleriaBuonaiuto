package com.gioielleriabuonaiuto.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "usero", schema = "public")
public class User {

    public User() {
    }

    public User(String email){
        this.email = email;
    }

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Basic
    @Column(name = "firstname", nullable = true, length = 20)
    private String firstname;


    @Basic
    @Column(name = "surname", nullable = true, length = 30)
    private String surname;

    @Basic
    @Column(name = "phonenumber", nullable = true, length = 13)
    private String phonenumber;


    @Basic
    @Column(name = "streetaddress", nullable = true, length = 100)
    private String streetaddress;

    @Basic
    @Column(name = "postalcode", nullable = true, length = 5)
    private long postalcode;

    @Basic
    @Column(name = "admin")
    private boolean admin;

    @Version
    private long version;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getStreetaddress() {
        return streetaddress;
    }

    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }

    public long getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(long postalcode) {
        this.postalcode = postalcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(phonenumber, user.phonenumber) &&
                Objects.equals(streetaddress, user.streetaddress) &&
                Objects.equals(postalcode, user.postalcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstname, surname, phonenumber, streetaddress, postalcode);
    }

}
