import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Tarea2 {

    public static void main(String[] args) throws IOException {

        //Carpeta base donde se creará todo (raíz del proyecto)
        String base = System.getProperty("user.dir") + File.separator + "familia";
        String[] abuelos = {"Abuelo", "Abuela"};
        String[] padres  = {"Padre", "Madre"};

        int contador = 1;

        for (String abuelo : abuelos) {
            for (String padre : padres) {

                File carpeta = new File(base + File.separator + abuelo + File.separator + padre);

                boolean creada = carpeta.mkdirs();
                System.out.println((creada ? "Creada: " : "Ya existía o no se pudo crear: ") + carpeta.getPath());

                File ficheroHijo = new File(carpeta, "Hijo" + contador + ".txt");
                contador++;
                File ficheroHija = new File(carpeta, "Hija" + contador + ".txt");
                contador++;

                if (ficheroHijo.createNewFile()) {
                    System.out.println("  -> Fichero creado: " + ficheroHijo.getName());
                } else {
                    System.out.println("  -> Ya existía: " + ficheroHijo.getName());
                }

                if (ficheroHija.createNewFile()) {
                    System.out.println("  -> Fichero creado: " + ficheroHija.getName());
                } else {
                    System.out.println("  -> Ya existía: " + ficheroHija.getName());
                }
            }
        }

        System.out.println("\nEstructura creada en: " + base);
        System.out.println("=".repeat(50));
        String nombreBuscado;

        if (args.length > 0) {
            nombreBuscado = args[0];
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("¿Qué fichero quieres borrar? (ej. Hijo3.txt): ");
            nombreBuscado = sc.nextLine();
            sc.close();
        }

        File carpetaBase = new File(base);
        boolean encontrado = buscarYBorrar(carpetaBase, nombreBuscado);

        if (!encontrado) {
            System.out.println("No se ha encontrado el fichero: " + nombreBuscado);
        }
    }

    public static boolean buscarYBorrar(File directorio, String nombreBuscado) {

        File[] contenido = directorio.listFiles();

        if (contenido == null) {
            return false;
        }

        for (File f : contenido) {
            if (f.isDirectory()) {
                if (buscarYBorrar(f, nombreBuscado)) {
                    return true; 
                }
            } else if (f.isFile() && f.getName().equalsIgnoreCase(nombreBuscado)) {
                boolean borrado = f.delete();
                if (borrado) {
                    System.out.println("Fichero borrado: " + f.getPath());
                } else {
                    System.out.println("No se pudo borrar: " + f.getPath());
                }
                return true;
            }
        }

        return false;
    }
}