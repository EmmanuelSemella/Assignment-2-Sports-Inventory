package com.Semella.Store;

import com.Semella.Entity.Store;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class StoreServiceImpl implements StoreService{
    private static final int MAX_CAPACITY = 27;
    private static final int INITIAL_CAPACITY = 18;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void clearList() {
        Query deleteFromStore = em.createNamedQuery("Store.clearAll");
        deleteFromStore.executeUpdate();
    }

    public List<Store> getAllByBuilder() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Store> query = builder.createQuery(Store.class);
        Root<Store> from = query.from(Store.class);
        TypedQuery<Store> s = em.createQuery(query.select(from));
        return s.getResultList();
    }

    @Override
    public List<Store> getStoreList() {
        List<Store> storeList =  em.createNamedQuery("Store.findAll", Store.class)
                .getResultList();

        if (storeList.size() < MAX_CAPACITY) {
            return storeList.stream()
                    .limit(INITIAL_CAPACITY)
                    .sorted()
                    .collect(Collectors.toList());
        } else {
            return storeList.stream()
                    .limit(MAX_CAPACITY)
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void addToList(Store store) {
        em.persist(store);
    }

    @Override
    public void removeFromList(Store store) {
        Store correspondingStore = em.createNamedQuery("Store.getByName", Store.class)
                .setParameter("name", store.getName()).getSingleResult();
        em.remove(correspondingStore);
    }
}
