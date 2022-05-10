/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agura.parserpuml.controlador;

import com.agura.parserpuml.modelo.Modelo;
import com.agura.parserpuml.vista.InterfazVista;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;

/**
 *
 * @author agustin
 */
public abstract class Controlador implements ActionListener, KeyListener, WindowListener{
    InterfazVista VISTA = null;
    Modelo MODELOAPP = null;
}
