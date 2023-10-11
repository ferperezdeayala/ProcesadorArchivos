package org.example;

import static org.example.ProcesadorArchivos.procesadorArchivos;

public class Main {

    /**
     * Este es el método main empleado para ejecutar el procesador de archivos.
     *
     */
    public static void main(String[] args) {

        procesadorArchivos("data.csv","template.txt");
    }
}