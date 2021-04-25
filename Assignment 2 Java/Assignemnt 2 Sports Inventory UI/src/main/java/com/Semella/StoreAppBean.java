package com.Semella;

import com.Semella.Store.StoreService;

import javax.ejb.EJB;
import javax.mail.Store;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class StoreAppBean implements Serializable {

    private String name;
    private String location;
    private List listofInventory;

    @EJB
    private StoreService storeService;

    public List<com.Semella.Entity.Store> getStoreList(){return storeService.getStoreList(); }

    public String addStore() {

        Store store = new Store(name, location, listofInventory);
        Optional<Store> storeExists = storeService.getStoreList().stream().filter(s ->
                s.getName().equals(name) && s.getLocation().equals(location)&& s.getlistOfInventory().equals(listofInventory)).findFirst();
        if (storeExists.isPresent()) {
            storeService.removeFromList(store);
        } else {
            storeService.addToList(store);
        }
        clearFields();
        return "playerList";
    }

    private void clearFields() {
        setName("");
        setLocation("");
        setListOfInventory("");
    }



    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getLocation(){return location;}
    public void setLocation(String location){this.location = location;}

    public List getListofInventory(){return listofInventory;}
    private void setListOfInventory(String s) {this.listofInventory = listofInventory;
    }
}
