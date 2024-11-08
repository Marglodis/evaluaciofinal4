package com.colegio.modelos;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una materia, con un nombre y una lista de notas asociadas.
 */
public class Materia {
    private MateriaEnum nombre;
    private List<Double> notas;

    /**
     * Crea una instancia de Materia con un nombre específico.
     *
     * @param nombre el nombre de la materia
     */
    public Materia(MateriaEnum nombre) {
        this.nombre = nombre;
        this.notas = new ArrayList<>();
    }

    // Getters y Setters
    public MateriaEnum getNombre() {
        return nombre;
    }

    public void setNombre(MateriaEnum nombre) {
        this.nombre = nombre;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }

    // Método para agregar una nueva nota a la materia
    public void agregarNota(double nota) {
        this.notas.add(nota);
    }

    @Override
    public String toString() {
        return nombre.toString() + " - Notas: " + notas.toString();
    }
}
