package com.Semella.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s")
@NamedQuery(name = "Store.getByName", query = "SELECT s from Store s where s.name = :name")
@NamedQuery(name = "Store.clearAll", query = "DELETE FROM Store")
public class Store implements Comparable<Store>, Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String name;
    private String location;
    private List listOfInventory;

    public Store(String name, String location,List listOfInventory) {
        this.name =name;
        this.location = location;
        this.listOfInventory =listOfInventory;
    }

    @OneToMany(mappedBy = "store", fetch = FetchType.EAGER)

    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sport='" + location + '\'' +
                ", quantity='" + listOfInventory + '\'' +
                '}';
    }

    @Override
    public int compareTo(Store o) {
        return 0;
    }
}
