package com.ordermate.components.listener;

/**
 * Created by edwin on 20/05/17.
 */

/* Generic Re-usable interface which can be used to signal item selection */
/* As of now it is used only for a single type, can be used for many types */

public interface ItemSelectedListener<T> {
    public void itemSelected(T t);
}
