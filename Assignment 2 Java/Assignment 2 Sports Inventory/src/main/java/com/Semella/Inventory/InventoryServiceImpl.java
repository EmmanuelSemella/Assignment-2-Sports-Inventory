package com.Semella.Inventory;

import com.Semella.Entity.Inventory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryServiceImpl implements InventoryService {
    private static final int MAX_CAPACITY = 27;
    private static final int INITIAL_CAPACITY = 18;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void clearList() {
        Query deleteFromInventory = em.createNamedQuery("Inventory.clearAll");
        deleteFromInventory.executeUpdate();
    }

    public List<Inventory> getAllByBuilder() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Inventory> query = builder.createQuery(Inventory.class);
        Root<Inventory> from = query.from(Inventory.class);
        TypedQuery<Inventory> i = em.createQuery(query.select(from));
        return i.getResultList();
    }

    @Override
    public List<Inventory> getInventoryList() {
        List<Inventory> inventoryList =  em.createNamedQuery("Inventory.findAll", Inventory.class)
                .getResultList();

        if (inventoryList.size() < MAX_CAPACITY) {
            return inventoryList.stream()
                    .limit(INITIAL_CAPACITY)
                    .sorted()
                    .collect(Collectors.toList());
        } else {
            return inventoryList.stream()
                    .limit(MAX_CAPACITY)
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void addToList(Inventory inventory) {
        em.persist(inventory);

    }

    @Override
    public void removeFromList(Inventory inventory) {
        Inventory correspondingInventory = em.createNamedQuery("Inventory.getByName", Inventory.class)
                .setParameter("name", inventory.getName()).getSingleResult();
        em.remove(correspondingInventory);

    }
}
