package controller;

import models.Coche;

import java.io.*;
import java.util.ArrayList;

public class GestionFicheros {
    File fichero = new File("src/main/resources/coches.dat");
    ArrayList<Coche> listaCoches = new ArrayList<>();

    public GestionFicheros() {
        //Creo manualmente el constructor vacio para realizar la comprobacion del archivo dat cuando se le invoca
        comprobarDat();
    }

    private void comprobarDat() {
        ObjectInputStream ois = null;

        if (fichero.exists()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(fichero));
                while (true) {
                    //añado el try/catch para poder capturar la excepcion END OF FILE y parar el bucle
                    try {
                        listaCoches = (ArrayList<Coche>) ois.readObject();
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error en la lectura ");
            } finally {
                try {
                    if (ois != null) ois.close(); //Se cierra el flujo de datos entrante
                } catch (IOException e) {
                    System.out.println("Error en el cierre de ObjectInputStream");
                }

            }

            long idMayor = 0L;

            for (Coche c : listaCoches) {
                if (c.getId() > idMayor) {
                    idMayor = c.getId();
                }

            }
            Coche.setIdContador(idMayor);

        } else {
            System.out.println("El archivo no existe, se creara uno nuevo");

        }
    }

    public boolean crearCoche(String matricula, String marca, String modelo, String color) {

        //Primero compruebo que no existe ningun coche con esa matricula
        for (Coche c : listaCoches) {
            if (c.getMatricula().equalsIgnoreCase(matricula)) {
                System.out.println("Ya existe un coche con la matrícula " + matricula);
                return false;
            }
        }
        // Si no se encuentra ningún coche con la misma matrícula, se añade uno nuevo
        Coche coche = new Coche(matricula, marca, modelo, color);
        listaCoches.add(coche);
        return true;
    }

    public boolean borrarCoche(Long id) {
        //este metodo devolvera true si se encuentra un coche con ese ID y es eliminado y false si no se encuentra el coche con ese ID
        Coche c = new Coche(id);
        return listaCoches.remove(c);

    }

    public String buscarPorId(Long id) {

        Coche aux = new Coche(id);
        for (Coche c : listaCoches) {
            if (c.equals(aux)) return c.toString();

        }
        return "No existe un coche con el ID " + id;

    }

    public void listarCoches() {
        for (Coche c : listaCoches) System.out.println(c.toString());
    }

    public void guardarDatos() {
        ObjectOutputStream ous = null;
        try {
            ous = new ObjectOutputStream(new FileOutputStream(fichero));
            ous.writeObject(listaCoches);
        } catch (IOException e) {
            System.out.println("Error al guardar");
        } finally {
            try {
                ous.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el flujo de datos.");
            }
        }
    }

    public void cargarDatos() {
        crearCoche("1234ABC", "Seat", "Ibiza", "Rojo");
        crearCoche("5678DEF", "Renault", "Clio", "Azul");
        crearCoche("9876GHI", "Ford", "Focus", "Verde");
        crearCoche("5432JKL", "Opel", "Astra", "Blanco");
    }

    public void exportarCSV() {
        File ficheroCSV = new File("src/main/resources/coches.csv");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(ficheroCSV));
            for (Coche c : listaCoches) {
                pw.println(c.getId() + ";" + c.getMatricula() + ";" + c.getMarca() + ";" + c.getModelo() + ";" + c.getColor());
            }
        } catch (IOException e) {
            System.out.println("Error al exportar a CSV");
        } finally {
            if (pw != null) pw.close();
        }
    }
}





