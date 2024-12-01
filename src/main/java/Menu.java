import controller.GestionFicheros;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        GestionFicheros gs = new GestionFicheros();
        Scanner leer = new Scanner(System.in);
        int opcion;
        do {
            pintarMenu();
            opcion = leer.nextInt();
            leer.nextLine(); //para limpiar el buffer del scanner

            long id; //Declaro aqui la variable id porque la voy a usar en dos case
            switch (opcion) {
                case 1:
                    System.out.println("Introduce los datos del coche que quieres añadir");

                    System.out.println("Matricula:");
                    String matricula = leer.nextLine();

                    System.out.println("Marca:");
                    String marca = leer.nextLine();

                    System.out.println("Modelo:");
                    String modelo = leer.nextLine();

                    System.out.println("Color:");
                    String color = leer.nextLine();

                    if (gs.crearCoche(matricula, marca, modelo, color)) {
                        System.out.println("Coche añadido");
                    } else System.out.println("Coche no añadido");

                    break;
                case 2:
                    System.out.println("Introduce el ID del coche que deseas borrar de la lista");
                    id = leer.nextLong();
                    leer.nextLine(); // limpiar buffer

                    if (gs.borrarCoche(id)) {
                        System.out.println("Coche borrado");
                    } else System.out.println("Coche no borrado");

                    break;

                case 3:
                    System.out.println("Introduce el ID del coche a mostrar");
                    id = leer.nextLong();
                    leer.nextLine();//limpiar buffer
                    System.out.println(gs.buscarPorId(id));
                    break;
                case 4:
                    System.out.println("Mostrando la lista completa.");
                    gs.listarCoches();
                    break;
                case 5:
                    System.out.println("Saliendo del programa");
                    gs.guardarDatos();
                    break;
                case 6:
                    System.out.println("Cargando datos de ejemplo");
                    gs.cargarDatos();
                    break;
                case 7:
                    System.out.println("Exportando a CSV");
                    gs.exportarCSV();
                    break;

                default:
                    System.out.println("opcion incorrecta");
            }

        } while (opcion != 5);

        System.out.println("FIN");
    }



    private static void pintarMenu() {
        System.out.println("""
    ======================================
               GESTIÓN DE COCHES
    ======================================
    Seleccione la opción a realizar:
    --------------------------------------
    1. Añadir nuevo coche
    2. Borrar coche por ID
    3. Consultar coche por ID
    4. Listado de coches
    5. Guardar y salir
    --------------------------------------
    6. Cargar datos de ejemplo
    7. Exportar a CSV
    ======================================
    """);

    }
}
