package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Setter
public class Coche implements Serializable {

    private static final long serialVersionUID = 12345L;
    private static long idContador = 0L;

    private long id;
    private String matricula, marca, modelo, color;


    //Sobreescribo los metodos equals y hascode para poder buscar coches por id de una manera mas optima comparando objetos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coche coche = (Coche) o;
        return id == coche.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    //sobreescribo tambien el metodo toString
    @Override
    public String toString() {
        return "ID: " + id +
                ", Matricula: " + matricula +
                ", Marca: " + marca +
                ", Modelo: " + modelo +
                ", Color: " + color;
    }



    //creo un constructor con todos los atributos menos el id que lo genero para que no se repita
    public Coche(String matricula, String marca, String modelo, String color) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.id = idGenerador();

    }

    // creo un constructor con solo el id para poder hacer busquedas por id comparando objetos
    public Coche(long id) {
        this.id = id;
    }

    private long idGenerador() {
         idContador++;
         return idContador;
    }

    public static void setIdContador(long idMayor) {
        Coche.idContador = idMayor;

    }


}


