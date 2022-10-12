package model;

import java.io.Serializable;

public class Programa implements Serializable {

    // Constructor vacío
    public Programa() {
    }

    // Constructor con parámetros
    public Programa(String nombre, String modalidad, String codigo) {
        this.nombre = nombre;
        this.modalidad = modalidad;
        this.codigo = codigo;
    }

    // Variables del objeto
    private String nombre;
    private String modalidad;
    private String codigo;

    //Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Método toString
    @Override
    public String toString() {
        return
                        "nombre: "+nombre
                        +", modalidad: "+modalidad
                        +", código: "+codigo;
    }
}
