/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agura.parserpuml;

import com.agura.parserpuml.controlador.Controlador;
import com.agura.parserpuml.controlador.ControladorImpl;
import com.agura.parserpuml.modelo.Modelo;
import com.agura.parserpuml.modelo.Parser;
import com.agura.parserpuml.vista.InterfazVista;
import com.agura.parserpuml.vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Window",
        id = "com.agura.parserpuml.PlantUML2Java"
)
@ActionRegistration(
        iconBase = "com/agura/parserpuml/PlantUml2Java.svg.png",
        displayName = "#CTL_PlantUML2Java"
)
@ActionReference(path = "Menu/Window", position = 0)
@Messages("CTL_PlantUML2Java=PlantUML a Java")
public final class PlantUML2Java implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Modelo modeloApp = new Parser();
                InterfazVista vista = new Principal();

                modeloApp.addObserver(vista);
                //controlador:
                Controlador control = new ControladorImpl(vista, modeloApp);

                //configuramos la vista para que pueda enviar los eventos de swing al controlador
                vista.setControlador(control);

                //y arrancamos la interfaz:
                vista.iniciaVista();
            }
        });
    }
}
