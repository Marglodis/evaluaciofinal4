package com.colegio.servicios;
import com.colegio.modelos.Alumno;
import com.colegio.modelos.Materia;

import com.colegio.modelos.MateriaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Clase de prueba unitaria para la clase AlumnoServicio.
 */

public class AlumnoServicioTest {
    @InjectMocks
    private AlumnoServicio alumnoServicio;

    @Mock
    private AlumnoServicio alumnoServicioMock;

    private Materia matematicas;
    private Materia lenguaje;
    private Alumno mapu;

    /**
     * Configura las instancias y mocks necesarios para las pruebas.
     */

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        matematicas = new Materia(MateriaEnum.MATEMATICAS);
        lenguaje = new Materia(MateriaEnum.LENGUAJE);

        mapu = new Alumno("12345678-9", "Mapu", "Perez", "Casita 1");
    }

    @Test
    @DisplayName("Crear alumno y verificar si se ha añadido")
    void crearAlumnoTest() {
        alumnoServicio.crearAlummno(mapu);
        Map<String, Alumno> alumnos = alumnoServicio.listarAlumnos();

        assertNotNull(alumnos);
        assertEquals(1, alumnos.size());
        assertEquals(mapu, alumnos.get("12345678-9"));
    }

    @Test
    @DisplayName("Agregar materia a un alumno existente")
    void agregarMateriaTest() {
        alumnoServicio.crearAlummno(mapu);
        alumnoServicio.agregarMateria("12345678-9", matematicas);

        List<Materia> materias = alumnoServicio.materiasPorAlumnos("12345678-9");
        assertEquals(1, materias.size());
        assertEquals(matematicas, materias.get(0));
    }

    @Test
    @DisplayName("Obtener materias de un alumno usando mock")
    void materiasPorAlumnosTest() {
        when(alumnoServicioMock.materiasPorAlumnos("12345678-9")).thenReturn(Arrays.asList(matematicas, lenguaje));

        List<Materia> materias = alumnoServicioMock.materiasPorAlumnos("12345678-9");

        assertEquals(2, materias.size());
        assertEquals(matematicas, materias.get(0));
        assertEquals(lenguaje, materias.get(1));
        verify(alumnoServicioMock, times(1)).materiasPorAlumnos("12345678-9");
    }

    @Test
    @DisplayName("Listar todos los alumnos")
    void listarAlumnosTest() {
        alumnoServicio.crearAlummno(mapu);

        Map<String, Alumno> alumnos = alumnoServicio.listarAlumnos();
        assertNotNull(alumnos);
        assertEquals(1, alumnos.size());
        assertEquals(mapu, alumnos.get("12345678-9"));
    }
}
