package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Universidad implements Serializable {

    // Constructor vacío
    public Universidad() {
    }

    // Constructor con parámetros
    public Universidad(ArrayList<Estudiante> estudiantes, ArrayList<Programa> programas) {
        this.estudiantes = estudiantes;
        this.programas = programas;
    }

    // Variables del objeto
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Programa> programas;

    // Getters y setters
    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public ArrayList<Programa> getProgramas() {
        return programas;
    }

    public void setProgramas(ArrayList<Programa> programas) {
        this.programas = programas;
    }
}
