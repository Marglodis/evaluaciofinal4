package com.colegio.servicios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromedioServicioTest {
    private PromedioServicio promedioServicio;

    @BeforeEach
    void setUp() {
        promedioServicio = new PromedioServicio();
    }

    @Test
    @DisplayName("Calcular promedio de una lista de notas")
    void calcularPromedioConNotas() {
        List<Double> notas = Arrays.asList(5.0, 6.5, 4.0);
        double resultado = promedioServicio.calcularPromedio(notas);
        assertEquals(5.17, resultado, 0.01, "El promedio debe ser aproximadamente 5.17");
    }

    @Test
    @DisplayName("Calcular promedio con lista vacía")
    void calcularPromedioListaVacia() {
        List<Double> notasVacias = Collections.emptyList();
        double resultado = promedioServicio.calcularPromedio(notasVacias);
        assertEquals(0.0, resultado, "El promedio de una lista vacía debe ser 0.0");
    }

    @Test
    @DisplayName("Calcular promedio con lista nula")
    void calcularPromedioListaNula() {
        double resultado = promedioServicio.calcularPromedio(null);
        assertEquals(0.0, resultado, "El promedio de una lista nula debe ser 0.0");
    }
}
