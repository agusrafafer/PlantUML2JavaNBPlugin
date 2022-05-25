/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agura.parserpuml.accion;

import com.agura.parserpuml.modelo.Modelo;
import com.agura.parserpuml.modelo.Parser;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author agustin
 */
public class ParserAccion extends Accion {

    public ParserAccion() {
    }

    @Override
    public void ejecutar(Modelo modelo, Object[]... paths) {
        try {
            Parser p = (Parser) modelo;
            Object[] arrayPaths = paths[0];
            for (Object arrayPath : arrayPaths) {
                String path = (String) arrayPath;
                String contenido = p.abrirArchivo(path);
                p.parsear(contenido, path);

            }
            modelo.notifyObservers("imprimeMensaje", "Clases Java generadas con exito", null);
        } catch (URISyntaxException | IOException ex) {
            modelo.notifyObservers("imprimeMensaje", null, "Ocurrio un error al tratar de generar las clases Java");
        }
    }

}
