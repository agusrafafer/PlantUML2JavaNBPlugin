/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agura.parserpuml.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author agustin
 */
public interface Subject {
    
    public static List<Observer> OBSERVERS = new ArrayList<>();
    
    default public void addObserver(Observer o){
        OBSERVERS.add(o);
    }

    default public void removeObserver(Observer o){
        Iterator it = OBSERVERS.iterator();

        while (it.hasNext()) {
            Observer obs = (Observer) it.next();
            if (obs.equals(o)){
                it.remove();
            }
        }
    }

//    default public void notifyObservers(float resultado){
//        for (Observer o : OBSERVERS) {
//            o.update(resultado);
//        }
//    }
    
    default public void notifyObservers(String... params) {
        for (Observer o : OBSERVERS) {
            o.update(params);
        }
    }
    
}
