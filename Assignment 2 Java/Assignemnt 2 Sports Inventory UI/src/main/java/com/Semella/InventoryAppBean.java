package com.Semella;

import com.Semella.Entity.Inventory;
import com.Semella.Inventory.InventoryService;

import javax.ejb.EJB;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class InventoryAppBean implements Serializable {

    @NotEmpty
    private String name;
    private String sport;
    private int quantity;
    private double pricePerUnit;
    private Date date;
    @EJB
    private InventoryService inventoryService;

    public List<Inventory> getInventoryList(){return inventoryService.getInventoryList();}

    public String addInventory(){
        Inventory inventory = new Inventory(name,sport,quantity,pricePerUnit,date);
        Optional<Inventory> inventoryExists = InventoryService.getInventoryList().stream.filter(i ->
                i.getName().equals(name) && i.getSport().equals(sport) && i.getQuantity(quantity)
                        && i.getPricePerUnit(pricePerUnit) && i.getDate(date)).findFirst();
        if(inventoryExists.isPresent()){
            inventoryService.removeFromList(inventory);
        }
        else {
            inventoryService.addToList(inventory);
        }
        clearFields();
        return "inventoryList";
    }

    private void clearFields(){
        setName("");
        setSport("");
        setQuantity(0);
        setPricePerUnit(0);
        setDate("");
    }



    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getSport(){return sport;}
    public void setSport(String sport){this.sport = sport;}

    public int getQuantity(){return  quantity;}
    public void setQuantity(int quantity){this.quantity = quantity;}

    public double getPricePerUnit(){return pricePerUnit;}
    public void setPricePerUnit(double pricePerUnit){this.pricePerUnit = quantity;}

    public Date getDate(){return date;}
    private void setDate(String date) {
    }




}
