/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agura.parserpuml.controlador;

import com.agura.parserpuml.modelo.Modelo;
import com.agura.parserpuml.vista.InterfazVista;
import com.agura.parserpuml.vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

/**
 *
 * @author agustin
 */
public class ControladorImpl extends Controlador {
    
    public ControladorImpl(InterfazVista vista, Modelo modeloApp) {
        VISTA = vista;
        MODELOAPP = modeloApp;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
//        try {
//            float numero1 = this.VISTA.getNumero1();
//            float numero2 = this.VISTA.getNumero2();
//
//            MODELOAPP.realizarAccion(e.getActionCommand());
//
//        } catch (Exception ex) {
//            VISTA.imprimeError(ex);
//        }
        try {
            MODELOAPP.actualizarVista(e.getActionCommand(), ((Principal) this.VISTA).modeloListaPathSel.toArray());
        } catch (Exception ex) {
            this.VISTA.imprimeMensaje(null, ex);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        verificarInputTxt(e);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        verificarInputTxt(e);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        verificarInputTxt(e);
    }
    
    public void verificarInputTxt(KeyEvent e) {
        char c = e.getKeyChar();
        
        if (!((c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
                || (c == KeyEvent.VK_ENTER) || (c == KeyEvent.VK_TAB)
                || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_DECIMAL)
                || (Character.isDigit(c)))) {
            e.consume();
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
        
    }

    @Override
    public void windowClosing(WindowEvent we) {
        try {
            MODELOAPP.actualizarVista("SalirAccion", ((Principal) this.VISTA).modeloListaPathSel.toArray());
        } catch (Exception ex) {
            this.VISTA.imprimeMensaje(null, ex);
        }
    }

    @Override
    public void windowClosed(WindowEvent we) {
        
    }

    @Override
    public void windowIconified(WindowEvent we) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        
    }

    @Override
    public void windowActivated(WindowEvent we) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        
    }
    
}
