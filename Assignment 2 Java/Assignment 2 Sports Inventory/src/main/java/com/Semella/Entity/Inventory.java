package com.Semella.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i")
@NamedQuery(name = "Inventory.getByName", query = "SELECT i from Inventory i where i.name = :name")
@NamedQuery(name = "Inventory.clearAll", query = "DELETE FROM Inventory")
public class Inventory implements Comparable<Inventory>, Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String sport;
    private int quantity;
    private double pricePerUnit;
    private Date date;

    @OneToMany(mappedBy = "inventory", fetch = FetchType.EAGER)

    @PrePersist
    void createdAt(){this.date = new Date();}

    @PreUpdate
    void updateAt(){this.date = new Date();}

    public Inventory(String name, String sport, int quantity, double pricePerUnit, Date date) {
        this.name = name;
        this.sport = sport;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.date = date;
    }

    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sport='" + sport + '\'' +
                ", quantity='" + quantity + '\'' +
                ", pricePerUnit='" + pricePerUnit + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public int compareTo(Inventory i) {
        return date.compareTo(i.date);
    }
}
