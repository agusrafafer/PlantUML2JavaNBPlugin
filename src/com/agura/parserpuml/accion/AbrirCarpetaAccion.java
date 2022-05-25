/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agura.parserpuml.accion;

import com.agura.parserpuml.modelo.Modelo;

/**
 *
 * @author agustin
 */
public class AbrirCarpetaAccion extends Accion {

    public AbrirCarpetaAccion() {
    }

    @Override
    public void ejecutar(Modelo modelo, Object[]... paths) {
        modelo.notifyObservers("abrirDialogoSeleccionarCarpeta");
    }

}
