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

    public static Accion getAccion(String nombreClase) {
        Accion accion = null;
        try {
            
            Class c = (Class) Class.forName(Accion.class.getPackage().getName() + "." + nombreClase);
//            Class c = (Class) Class.forName(nombreClase);
            Object obj = c.newInstance();
            accion = (Accion) obj;
        } catch (IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println(e);
        }
        return accion;
    }
}
