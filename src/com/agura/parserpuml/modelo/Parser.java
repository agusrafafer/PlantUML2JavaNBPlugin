/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agura.parserpuml.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author agustin
 */
public class Parser extends Modelo {

    public List<Clase> clases = new ArrayList<>();

    public String abrirArchivo(String ruta) throws URISyntaxException, IOException {
        File archivo = new File(ruta);
        StringBuilder sb;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            sb = new StringBuilder();
            br.lines().forEach(cad -> {
                sb.append(cad);
                sb.append("\n");
            });
        }
        return sb.toString();
    }

    public void parsear(String contArchivo, String ruta) throws IOException {
        //Verificamos que la cadena comience con @startuml y finalice con @enduml
        if (!contArchivo.startsWith("@startuml") && !contArchivo.endsWith("@enduml")) {
            System.out.println("El archivo puml no parece estar bien formado");
            return;
        }
        parsearElementos(contArchivo);
        int posUltSep = ruta.lastIndexOf(File.separator);
        if (posUltSep > -1) {
            generarArchivos(ruta.substring(0, posUltSep));
        } else {
            throw new IOException("Error en el separador de paths del sistema operativo");
        }
    }

    private void generarArchivos(String ruta) throws IOException {
        BufferedWriter bw;
        StringBuilder sb;
        for (Clase clase : this.clases) {
            File archivo = new File(ruta + File.separator + clase.getNombreArchivo());
            bw = new BufferedWriter(new FileWriter(archivo));

            sb = new StringBuilder();
            String paquete = clase.getPaquete();
            if (paquete != null && !paquete.isEmpty()) {
                sb.append("package ");
                sb.append(clase.getPaquete());
                sb.append(";\n");
            }
            sb.append("\n");
            sb.append("class ");
            sb.append(clase.getNombre());
            sb.append("{\n");
            for (Atributo atributo : clase.getAtributos()) {
                sb.append("\t");
                sb.append(atributo.getVisibilidad());
                sb.append(" ");
                sb.append(atributo.getFirma());
                sb.append(";\n");
            }
            for (Metodo metodo : clase.getMetodos()) {
                sb.append("\t");
                sb.append(metodo.getVisibilidad());
                sb.append(" ");
                sb.append(metodo.getFirma());
                sb.append(";\n");
            }
            sb.append("}\n");
            String contArchivo = sb.toString();
            bw.write(contArchivo, 0, contArchivo.length());
            bw.flush();
            bw.close();
        }

    }

    private void parsearElementos(String contenidoArchivo) {
        //int posExtends = contenidoArchivo.indexOf("-|>");
        StringTokenizer st = new StringTokenizer(contenidoArchivo, "\n");
        String paquete = "";
        while (st.hasMoreTokens()) {
            //System.out.println(st.nextToken());
            String token = st.nextToken();
            if (token.contains("package")) {
                if (token.contains("{")) {
                    paquete = token.substring(token.indexOf("package") + 8, token.indexOf("{"));
                } else {
                    paquete = token.substring(token.indexOf("package") + 8);
                }
                paquete = paquete.trim();
            } else if (token.contains("class")) {
                conformarElemento(st, token, "class", paquete);
            } else if (token.contains("interface")) {
                conformarElemento(st, token, "interface", paquete);
            } else if (token.contains("}")) {
                paquete = "";
            } else if (token.contains("-|>")) {
                String clase1 = token.substring(0, token.indexOf("-")).trim();
                String clase2 = token.substring(token.indexOf("|>") + 3).trim();
                int posClase1 = this.clases.indexOf(new Clase(clase1));
                this.clases.get(posClase1).setNombre(clase1 + " extends " + clase2);
            } else if (token.contains("<|-")) {
                String clase1 = token.substring(0, token.indexOf("<|")).trim();
                String clase2 = token.substring(token.lastIndexOf("-") + 1).trim();
                int posClase2 = this.clases.indexOf(new Clase(clase2));
                this.clases.get(posClase2).setNombre(clase2 + " extends " + clase1);
            } else if (token.contains(".|>")) {
                String clase1 = token.substring(0, token.indexOf(".")).trim();
                String clase2 = token.substring(token.indexOf("|>") + 3).trim();
                int posClase1 = this.clases.indexOf(new Clase(clase1));
                this.clases.get(posClase1).setNombre(clase1 + " implements " + clase2);
            } else if (token.contains("<|.")) {
                String clase1 = token.substring(0, token.indexOf("<|")).trim();
                String clase2 = token.substring(token.lastIndexOf(".") + 1).trim();
                int posClase2 = this.clases.indexOf(new Clase(clase2));
                this.clases.get(posClase2).setNombre(clase2 + " implements " + clase1);
            }
        }
    }

    /**
     * *
     * Metodo que permite conformar la abstraccion correcta (clase o interfaz)
     * con sus metodos y atributos
     *
     * @param st El StringTokenizer para poder analizar atributos y metodos
     * interiores de una clase o interfaz si es que los tiene
     * @param token El token principal a ser analizado
     * @param tipoClase El tipo de clase a conformar: class o interfaz
     * @param paquete El paquete al que pertenece la clase o interfaz
     */
    private void conformarElemento(StringTokenizer st, String token, String tipoClase, String paquete) {
        Clase claseObj = new Clase(new ArrayList<>(), new ArrayList<>());
        claseObj.setPaquete(paquete);
        String clase;
        if (tipoClase.equals("class")) {
            clase = token.substring(token.indexOf("class") + 6);// ej: class NombreClase
            claseObj.setEsInterfaz(false);
        } else {
            clase = token.substring(token.indexOf("interface") + 10);// ej: interface NombreInterface
            claseObj.setEsInterfaz(true);
        }
        clase = clase.trim();
        if (clase.contains("{")) {
            clase = clase.substring(0, clase.indexOf("{"));
            claseObj.setNombre(clase);
            claseObj.setNombreArchivo(clase + ".java");
            token = st.nextToken();
            token = token.trim();
            while (st.hasMoreTokens()) {
                if (token.contains("(")) {
                    //es un metodo
                    Metodo metodo = new Metodo();

                    if (token.startsWith("+") || token.startsWith("-") || token.startsWith("#")) {
                        String visibilidad = token.trim().substring(0, 1);
                        metodo.setFirma(token.substring(1).replaceAll(";", "").trim());
                        metodo.setVisibilidad(visibilidad.equals("+") ? "public" : visibilidad.equals("-") ? "private" : "protected");
                    } else {
                        //El metodo es protegido
                        metodo.setFirma(token.replaceAll(";", "").trim());
                        metodo.setVisibilidad("protected");
                    }
                    claseObj.getMetodos().add(metodo);
                } else {
                    //es un atributo
                    Atributo atributo = new Atributo();
                    if (token.startsWith("+") || token.startsWith("-") || token.startsWith("#")) {
                        String visibilidad = token.trim().substring(0, 1);
                        atributo.setFirma(token.substring(1).replaceAll(";", "").trim());
                        atributo.setVisibilidad(visibilidad.equals("+") ? "public" : visibilidad.equals("-") ? "private" : "protected");
                    } else {
                        //El atributo es protegido
                        atributo.setFirma(token.replaceAll(";", "").trim());
                        atributo.setVisibilidad("protected");
                    }
                    claseObj.getAtributos().add(atributo);
                }
                token = st.nextToken();
                token = token.trim();
                if (token.contains("}")) {
                    break;
                }
            }
        } else {
            //La clase no tiene ni atributos ni metodos
            String nombreClase = clase;
            String nombreClaseSinExtends = clase;
            if (nombreClase.contains("extends")) {
                nombreClaseSinExtends = nombreClase.substring(0, nombreClase.indexOf("extends"));
                nombreClaseSinExtends = nombreClaseSinExtends.trim();
            }
            claseObj.setNombre(nombreClase);
            claseObj.setNombreArchivo(nombreClaseSinExtends + ".java");
        }

        this.clases.add(claseObj);
    }

}
