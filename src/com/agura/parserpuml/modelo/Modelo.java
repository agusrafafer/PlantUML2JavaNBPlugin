/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agura.parserpuml.modelo;

import com.agura.parserpuml.accion.Accion;

/**
 *
 * @author agustin
 */
public abstract class Modelo  implements Subject{

    public void actualizarVista(String nombreClaseAccion, Object[]... paths) {
        Accion accion = Accion.getAccion(nombreClaseAccion);
        accion.ejecutar(this, paths);
       
    }
}
