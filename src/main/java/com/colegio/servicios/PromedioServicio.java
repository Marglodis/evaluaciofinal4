package com.colegio.servicios;

import java.util.List;

public class PromedioServicio {
    /**
     * Calcula el promedio de una lista de notas.
     * Si la lista es nula o está vacía, el método retorna 0.0.
     *
     * @param notas la lista de notas a promediar
     * @return el promedio de las notas, o 0.0 si la lista es nula o está vacía
     */
    public double calcularPromedio(List<Double> notas) {
        if (notas == null || notas.isEmpty()) {
            return 0.0;
        }
        return notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}
