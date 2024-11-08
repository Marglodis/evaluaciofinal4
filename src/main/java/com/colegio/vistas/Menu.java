package com.colegio.vistas;

import com.colegio.modelos.Alumno;
import com.colegio.modelos.Materia;
import com.colegio.modelos.MateriaEnum;
import com.colegio.servicios.AlumnoServicio;
import com.colegio.servicios.ArchivoServicio;

import java.util.List;

public class Menu extends MenuTemplate{
    private AlumnoServicio alumnoServicio = new AlumnoServicio();
    private ArchivoServicio archivoServicio = new ArchivoServicio();

    @Override
    public void mostrarMenu() {
        System.out.println("1. Crear Alumnos");
        System.out.println("2. Listar Alumnos");
        System.out.println("3. Agregar Materias");
        System.out.println("4. Agregar Notas");
        System.out.println("5. Salir");
        System.out.println("6. Exportar Datos");
        System.out.print("Selección: ");
    }

    @Override
    public void exportarDatos() {
        System.out.println("--- Exportar Datos ---");
        System.out.print("Ingresa la ruta en donde se exportará el archivo promedios.txt: ");
        String ruta = scanner.nextLine();
        archivoServicio.exportarDatos(alumnoServicio.listarAlumnos(), ruta);
        System.out.println("Datos exportados correctamente.");
    }

    @Override
    public void crearAlummno() {
        System.out.println("--- Crear Alumno ---");
        System.out.print("Ingresa RUT: ");
        String rut = scanner.nextLine();
        System.out.print("Ingresa nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingresa apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingresa dirección: ");
        String direccion = scanner.nextLine();

        Alumno alumno = new Alumno(rut, nombre, apellido, direccion);
        alumnoServicio.crearAlummno(alumno);
        System.out.println("¡Alumno agregado!");
    }

    @Override
    public void agregarMateria() {
        System.out.println("--- Agregar Materia ---");
        System.out.print("Ingresa rut del Alumno: ");
        String rut = scanner.nextLine();

        System.out.println("Selecciona una materia:");
        System.out.println("1. MATEMATICAS");
        System.out.println("2. LENGUAJE");
        System.out.println("3. CIENCIA");
        System.out.println("4. HISTORIA");

        int opcionMateria = Integer.parseInt(scanner.nextLine());

        // Mapear la opción seleccionada al valor de MateriaEnum
        MateriaEnum materiaEnumSeleccionada;
        switch (opcionMateria) {
            case 1:
                materiaEnumSeleccionada = MateriaEnum.MATEMATICAS;
                break;
            case 2:
                materiaEnumSeleccionada = MateriaEnum.LENGUAJE;
                break;
            case 3:
                materiaEnumSeleccionada = MateriaEnum.CIENCIA;
                break;
            case 4:
                materiaEnumSeleccionada = MateriaEnum.HISTORIA;
                break;
            default:
                System.out.println("Opción inválida");
                return; // Terminamos el método si la opción es inválida
        }

        // Crear la materia con el valor correspondiente de MateriaEnum
        Materia materia = new Materia(materiaEnumSeleccionada);
        alumnoServicio.agregarMateria(rut, materia);
        System.out.println("¡Materia agregada!");
    }

    @Override
    public void agregarNotaPasoUno() {
        System.out.println("--- Agregar Nota ---");
        System.out.print("Ingresa rut del Alumno: ");
        String rut = scanner.nextLine();

        // Obtener las materias del alumno
        List<Materia> materias = alumnoServicio.materiasPorAlumnos(rut);

        if (materias.isEmpty()) {
            System.out.println("El alumno no tiene materias agregadas.");
            return;  // Terminar el método si el alumno no tiene materias
        }

        // Mostrar las materias disponibles para el alumno
        System.out.println("Alumno tiene las siguientes materias agregadas:");
        for (int i = 0; i < materias.size(); i++) {
            System.out.println((i + 1) + ". " + materias.get(i));  // Mostrar el índice + 1 para que la numeración sea más natural
        }

        // Solicitar la selección de la materia
        int materiaSeleccionada = -1;
        while (materiaSeleccionada < 0 || materiaSeleccionada >= materias.size()) {
            try {
                System.out.print("Seleccionar materia (1-" + materias.size() + "): ");
                materiaSeleccionada = Integer.parseInt(scanner.nextLine()) - 1;  // Restamos 1 para ajustar al índice real

                // Verificar si el índice está fuera del rango válido
                if (materiaSeleccionada < 0 || materiaSeleccionada >= materias.size()) {
                    System.out.println("Opción inválida. Por favor, selecciona un número válido entre 1 y " + materias.size() + ".");
                }
            } catch (NumberFormatException e) {
                // Manejo de error si el usuario ingresa un valor que no es un número
                System.out.println("Por favor ingresa un número válido.");
            }
        }

        // Solicitar la nota
        System.out.print("Ingresar nota: ");
        double nota = Double.parseDouble(scanner.nextLine());

        // Agregar la nota a la materia seleccionada
        alumnoServicio.agregarNota(rut, materiaSeleccionada, nota);
        System.out.println("¡Nota agregada!");
    }

    @Override
    public void listarAlummnos() {
        System.out.println("--- Listar Alumnos ---");
        alumnoServicio.listarAlumnos().forEach((rut, alumno) -> {
            System.out.println(alumno);
        });
    }

    @Override
    public void terminarPrograma() {
        System.out.println("¡Hasta luego!");
    }
}
