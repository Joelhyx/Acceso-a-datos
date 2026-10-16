import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Tarea5 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    crearFichero();
                    break;
                case 2:
                    anadirFinal();
                    break;
                case 3:
                    anadirPrincipio();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            System.out.println();

        } while (opcion != 0);

        sc.close();
    }

    // ------------------------------------------------------
    // Menú
    // ------------------------------------------------------
    public static void mostrarMenu() {
        System.out.println("===============================");
        System.out.println(" GESTOR DE FICHEROS DE TEXTO");
        System.out.println("===============================");
        System.out.println("1. Crear fichero");
        System.out.println("2. Añadir texto al final");
        System.out.println("3. Añadir texto al principio");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    public static int leerOpcion() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // opción inválida
        }
    }

    // ------------------------------------------------------
    // 1. CREAR
    // ------------------------------------------------------
    public static void crearFichero() throws IOException {

        System.out.print("Nombre del fichero (sin extensión): ");
        String nombre = sc.nextLine().trim();

        File fichero = new File(nombre + ".txt");

        if (fichero.exists()) {
            System.out.println("Ya existe un fichero con ese nombre. Operación cancelada.");
            return;
        }

        boolean creado = fichero.createNewFile();
        if (!creado) {
            System.out.println("No se ha podido crear el fichero.");
            return;
        }

        System.out.println("Fichero creado: " + fichero.getAbsolutePath());
        System.out.println("Escribe el contenido. Escribe FIN en una línea para terminar:");

        try (FileWriter fw = new FileWriter(fichero);
                BufferedWriter bw = new BufferedWriter(fw)) {

            String linea = sc.nextLine();
            while (!linea.equalsIgnoreCase("FIN")) {
                bw.write(linea);
                bw.newLine();
                linea = sc.nextLine();
            }
        }

        System.out.println("Contenido guardado correctamente.");
    }

    // ------------------------------------------------------
    // 2. AÑADIR AL FINAL
    // ------------------------------------------------------
    public static void anadirFinal() throws IOException {

        System.out.print("Nombre del fichero (sin extensión): ");
        String nombre = sc.nextLine().trim();

        File fichero = new File(nombre + ".txt");

        if (!fichero.exists()) {
            System.out.println("El fichero no existe.");
            return;
        }

        System.out.println("Escribe el texto a añadir al final. Escribe FIN para terminar:");

        try (FileWriter fw = new FileWriter(fichero, true);
                BufferedWriter bw = new BufferedWriter(fw)) {

            String linea = sc.nextLine();
            while (!linea.equalsIgnoreCase("FIN")) {
                bw.write(linea);
                bw.newLine();
                linea = sc.nextLine();
            }
        }

        System.out.println("Texto añadido al final correctamente.");
    }

    // ------------------------------------------------------
    // 3. AÑADIR AL PRINCIPIO
    // ------------------------------------------------------
    public static void anadirPrincipio() throws IOException {

        System.out.print("Nombre del fichero (sin extensión): ");
        String nombre = sc.nextLine().trim();

        File fichero = new File(nombre + ".txt");

        if (!fichero.exists()) {
            System.out.println("El fichero no existe.");
            return;
        }

        System.out.println("Escribe el texto a añadir al principio. Escribe FIN para terminar:");

        StringBuilder textoNuevo = new StringBuilder();
        String linea = sc.nextLine();
        while (!linea.equalsIgnoreCase("FIN")) {
            textoNuevo.append(linea).append(System.lineSeparator());
            linea = sc.nextLine();
        }

        StringBuilder contenidoActual = new StringBuilder();
        try (FileReader fr = new FileReader(fichero);
                BufferedReader br = new BufferedReader(fr)) {

            String l = br.readLine();
            while (l != null) {
                contenidoActual.append(l).append(System.lineSeparator());
                l = br.readLine();
            }
        }

        try (FileWriter fw = new FileWriter(fichero);
                BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(textoNuevo.toString());
            bw.write(contenidoActual.toString());
        }

        System.out.println("Texto añadido al principio correctamente.");
    }
}