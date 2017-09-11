package udemy.recyclerview.curso.udemy.adolfo.com.recyclerview.models;

/**
 * Created by Adolfo Chavez on 07/09/2017.
 */

public class Amigo {

    private String nombre;
    private String apellido;
    private int edad;

    public Amigo() {
    }

    public Amigo(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
