package com.colegio.servicios;

import com.colegio.modelos.Alumno;
import com.colegio.modelos.Materia;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Servicio para exportar datos de alumnos y sus materias a un archivo.
 */
public class ArchivoServicio {

    PromedioServicio promedioServicio = new PromedioServicio();


    /**
     * Exporta los datos de los alumnos y sus promedios en cada materia a un archivo dentro del proyecto.
     * Cada alumno y sus materias se escriben en líneas separadas.
     *
     * @param alumnos un mapa que contiene los alumnos, donde la clave es el RUT del alumno y el valor es el objeto Alumno
     * @param ruta    la ruta del archivo donde se exportarán los datos
     */
    public void exportarDatos(Map<String, Alumno> alumnos, String ruta) {
        try (FileWriter writer = new FileWriter(ruta)) {
            for (Alumno alumno : alumnos.values()) {
                writer.write("Alumno: " + alumno.getRut() + " - " + alumno.getNombre() + "\n");
                for (Materia materia : alumno.getMaterias()) {
                    double promedio = promedioServicio.calcularPromedio(materia.getNotas());
                    writer.write("Materia: " + materia.getNombre() + " - Promedio: " + promedio + "\n");
                }
            }
            System.out.println("Datos exportados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al exportar los datos: " + e.getMessage());
        }
    }
}
