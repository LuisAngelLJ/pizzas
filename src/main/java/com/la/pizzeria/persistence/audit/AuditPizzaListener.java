package com.la.pizzeria.persistence.audit;

import com.la.pizzeria.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {
    private PizzaEntity currentValue;

    //después de que se haga un select y se cargue a un entity
    @PostLoad
    public void postLoad(PizzaEntity entity) {
        System.out.println("Post Load");
        this.currentValue = SerializationUtils.clone(entity);//el entity debe implementar de serializable
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity entity) {
        System.out.println("Post persist or update");
        System.out.println("Old value: " + this.currentValue.toString());
        System.out.println("New value: " + entity.toString());
    }

    @PreRemove
    public void onPreDelete(PizzaEntity entity) {
        System.out.println(entity.toString());
    }
}
