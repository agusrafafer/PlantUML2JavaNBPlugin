/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agura.parserpuml.accion;

import com.agura.parserpuml.modelo.Modelo;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author agustin
 */
public abstract class Accion {
    public abstract void ejecutar(Modelo modelo, Object[]... paths);
    
    public static Accion getAccion(String nombreClase){
        Accion accion = null;
        try {            
            accion = (Accion) Class.forName(Accion.class.getPackageName() + "." + nombreClase).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            System.err.println(e);
        }
        return accion;
    }
}
