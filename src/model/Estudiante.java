package model;

import java.io.Serializable;

public class Estudiante implements Serializable {

    // Constructor vacío
    public Estudiante() {
    }

    // Constructor con parámetros
    public Estudiante(String nombre, String id, String nota1, String nota2, String nota3) {
        this.nombre = nombre;
        this.id = id;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    // Variables del objeto
    private String nombre;
    private String id;
    private String nota1;
    private String nota2;
    private String nota3;

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        this.nota1 = nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }

    public String getNota3() {
        return nota3;
    }

    public void setNota3(String nota3) {
        this.nota3 = nota3;
    }

    // Método toString
    @Override
    public String toString() {
        return
                "nombre: "+nombre
                +", identificación: "+id
                +", nota 1: "+nota1
                +", nota 2: "+nota2
                +", nota 3: "+nota3;
    }
}
