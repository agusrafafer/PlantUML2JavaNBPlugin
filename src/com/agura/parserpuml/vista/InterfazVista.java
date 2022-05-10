/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agura.parserpuml.vista;

import com.agura.parserpuml.controlador.Controlador;
import com.agura.parserpuml.modelo.Observer;
import java.io.File;



/**
 *
 * @author agustin
 */
public interface InterfazVista extends Observer {

    default void setControlador(Controlador c){
        
    }

    default void iniciaVista(){
        
    }

    default void imprimeMensaje(String mensaje, Exception e){
        
    }
    
    default void abrirDialogoSeleccionarCarpeta() {
        
    }
    
    default void quitarPathCarpeta() {
        
    }
    
    default void limpiaVista(){
        
    }
    
    default File seleccionarCarpeta(){
        return null;
    }
    
    default String pathCarpetaSeleccionada(){
        return null;
    }
    
    default File [] archivosSeleccionados() {
        return null;
    }
    
    default void cierraSistema() {
        
    }
}
