package org.mixit.kotlin.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Hotel implements Serializable {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private City city;

    private String name;

    private String address;

    private String zip;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
    private Set<Review> reviews;

    protected Hotel() {
    }

    public Hotel(City city, String name) {
        this.city = city;
        this.name = name;
    }

    public City getCity() {
        return this.city;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getZip() {
        return this.zip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
