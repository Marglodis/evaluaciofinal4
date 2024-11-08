package com.colegio.servicios;

import com.colegio.modelos.Alumno;
import com.colegio.modelos.Materia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio para gestionar alumnos y sus materias.
 */

public class AlumnoServicio {

    private Map<String, Alumno> listaAlumnos = new HashMap<>();

    /**
     * Agrega un nuevo alumno al sistema.
     *
     * @param alumno el alumno a agregar
     */
    public void crearAlummno(Alumno alumno) {
        listaAlumnos.put(alumno.getRut(), alumno);
    }

    /**
     * Retorna la lista de todos los alumnos.
     *
     * @return un mapa de los alumnos, donde la clave es el RUT del alumno
     */
    public Map<String, Alumno> listarAlumnos() {
        return listaAlumnos;
    }

    /**
     * Agrega una materia a un alumno específico.
     *
     * @param rut     el RUT del alumno al que se le agregará la materia
     * @param materia la materia a agregar
     */
    public void agregarMateria(String rut, Materia materia) {
        Alumno alumno = listaAlumnos.get(rut);
        if (alumno != null) {
            alumno.getMaterias().add(materia);
        }
    }

    /**
     * Retorna la lista de materias de un alumno específico.
     *
     * @param rut el RUT del alumno
     * @return la lista de materias del alumno o {@code null} si el alumno no existe
     */
    public List<Materia> materiasPorAlumnos(String rut) {
        return listaAlumnos.get(rut).getMaterias();
    }

    /**
     * Agrega una nota a una materia específica de un alumno.
     *
     * @param rut          el RUT del alumno
     * @param materiaIndex el índice de la materia en la lista de materias del alumno
     * @param nota         la nota a agregar a la materia
     */
    public void agregarNota(String rut, int materiaIndex, double nota) {
        Alumno alumno = listaAlumnos.get(rut);
        if (alumno != null) {
            Materia materia = alumno.getMaterias().get(materiaIndex);  // Accedemos a la materia por índice
            materia.agregarNota(nota);  // Agregamos la nota a la materia seleccionada
        }
    }

}
