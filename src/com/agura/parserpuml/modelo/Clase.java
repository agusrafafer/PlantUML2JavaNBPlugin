/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agura.parserpuml.modelo;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author agustin
 */
public class Clase {

    private String nombre;
    private String nombreArchivo;
    private String paquete;
    private List<Atributo> atributos;
    private List<Metodo> metodos;
    private boolean esInterfaz;

    public Clase() {
    }

    public Clase(List<Atributo> atributos, List<Metodo> metodos) {
        this.atributos = atributos;
        this.metodos = metodos;
    }

    public Clase(String nombre) {
        this.nombre = nombre;
    }

    public Clase(String nombre, List<Atributo> atributos, List<Metodo> metodos) {
        this.nombre = nombre;
        this.atributos = atributos;
        this.metodos = metodos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    public List<Metodo> getMetodos() {
        return metodos;
    }

    public void setMetodos(List<Metodo> metodos) {
        this.metodos = metodos;
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public boolean isEsInterfaz() {
        return esInterfaz;
    }

    public void setEsInterfaz(boolean esInterfaz) {
        this.esInterfaz = esInterfaz;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Clase other = (Clase) obj;
        return Objects.equals(this.nombre, other.nombre);
    }

    

}
