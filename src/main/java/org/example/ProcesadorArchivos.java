package org.example;

import java.io.*;
import java.util.ArrayList;
/**
 * La clase ProcesadorArchivos nos da lo necesario para poder procesar archivos .csv y generar los archivos de texto usando
 * la plantilla template.txt.
 */

public class ProcesadorArchivos {

    /**
     * Este método crea una carpeta salida creando tantos documentos .txt como clientes haya usando la información
     * de la plantilla "template.txt"  y los datos de cada cliente en "data.csv".
     *
     * @param archivoData  Se refiere al archivo .csv que contiene los datos de las personas.
     * @param archivoTemplate  Se refiere al archivo .txt que contiene la plantilla del correo electronico.
     */
    public static void procesadorArchivos(String archivoData, String archivoTemplate){
        File archivo = new File(archivoData);

        try(BufferedReader brArchivo = new BufferedReader(new FileReader(archivo))){
            String leerArchivo;
            while ((leerArchivo = brArchivo.readLine())!=null) {
                String[] datosArchivo = leerArchivo.split(",");
                if(datosArchivo.length>=5){
                    ArrayList<String> ListaPlantillas = new ArrayList<>();
                    String id = datosArchivo[0];
                    String empresa = datosArchivo[1];
                    String ciudad = datosArchivo[2];
                    String correo = datosArchivo[3];
                    String empleado = datosArchivo[4];

                    try(BufferedReader brPlantilla = new BufferedReader(new FileReader(archivoTemplate))){
                        String leerPlantilla;
                        while((leerPlantilla = brPlantilla.readLine())!=null){
                            leerPlantilla = leerPlantilla.replace("%%2%%", ciudad);
                            leerPlantilla = leerPlantilla.replace("%%3%%", correo);
                            leerPlantilla = leerPlantilla.replace("%%4%%", empresa);
                            leerPlantilla = leerPlantilla.replace("%%5%%", empleado);

                            ListaPlantillas.add(leerPlantilla+"\n");
                            System.out.println(leerPlantilla);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("Error " + e);
                    }

                    File salida = new File("salida");
                    salida.mkdir();

                    try(BufferedWriter templatecorreo = new BufferedWriter(new FileWriter("salida/template-" + id+".txt"))){
                        for (String nuevaPlantilla : ListaPlantillas){
                            templatecorreo.write(nuevaPlantilla);
                            templatecorreo.flush();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("Error " + e);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error "+ e);
        }
    }
}
