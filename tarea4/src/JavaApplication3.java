import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class JavaApplication3 {

    public static void main(String[] args) throws IOException {

        String ruta = System.getProperty("user.dir");
        if (args.length > 0)
            ruta = args[0];

        File fich = new File(ruta);

        if (!fich.exists()) {
            System.out.println("No existe el fichero o directorio " + ruta);
            return;
        }

        String textoBuscado;
        if (args.length > 1) {
            textoBuscado = args[1];
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("¿Qué texto quieres buscar dentro de los ficheros?: ");
            textoBuscado = sc.nextLine();
            sc.close();
        }

        if (fich.isFile()) {
            mostrarInformacion(fich);
            contarVocalesBufferedReader(fich);
            buscarTexto(fich, textoBuscado);
        } else {
            System.out.println(" " + ruta + " es un directorio. Contenidos:");
            File[] ficheros = fich.listFiles();
            if (ficheros != null) {
                for (File f : ficheros) {
                    String texto = f.isDirectory() ? "/" : f.isFile() ? "_" : "?";
                    System.out.println(texto + " " + f.getName() + "  (" + f.length() + " bytes)");

                    if (f.isFile()) {
                        mostrarInformacion(f);
                        contarVocalesBufferedReader(f);
                        buscarTexto(f, textoBuscado);
                    }
                }
            } else {
                System.out.println("  (no se pudo leer el contenido, puede que falten permisos)");
            }
        }
    }

    public static void mostrarInformacion(File f) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.println("===============================================");
        System.out.println("INFORMACIÓN DE: " + f.getPath());
        System.out.println("===============================================");
        System.out.println("Nombre              : " + f.getName());
        System.out.println("Ruta absoluta        : " + f.getAbsolutePath());
        System.out.println("Tamaño en bytes      : " + f.length());
        System.out.println("Última modificación  : " + sdf.format(new Date(f.lastModified())));
    }

    public static void contarVocalesBufferedReader(File f) throws IOException {

        int[] contador = new int[5]; // a, e, i, o, u

        try (FileReader fil = new FileReader(f);
                BufferedReader brf = new BufferedReader(fil)) {

            String line = brf.readLine();
            while (line != null) {
                String linea = line.toLowerCase();
                for (int i = 0; i < linea.length(); i++) {
                    switch (linea.charAt(i)) {
                        case 'a':
                            contador[0]++;
                            break;
                        case 'e':
                            contador[1]++;
                            break;
                        case 'i':
                            contador[2]++;
                            break;
                        case 'o':
                            contador[3]++;
                            break;
                        case 'u':
                            contador[4]++;
                            break;
                    }
                }
                line = brf.readLine();
            }
        }

        System.out.println("--- Conteo de vocales ---");
        System.out.println("  a: " + contador[0] + "  e: " + contador[1] + "  i: " + contador[2]
                + "  o: " + contador[3] + "  u: " + contador[4]);
    }

    public static void buscarTexto(File f, String textoBuscado) throws IOException {

        if (textoBuscado == null || textoBuscado.isEmpty()) {
            return;
        }

        System.out.println("--- Búsqueda de \"" + textoBuscado + "\" ---");

        int numeroLinea = 0;
        int totalApariciones = 0;

        try (FileReader fil = new FileReader(f);
                BufferedReader brf = new BufferedReader(fil)) {

            String line = brf.readLine();

            while (line != null) {
                numeroLinea++;

                int columna = line.indexOf(textoBuscado);
                while (columna != -1) {
                    totalApariciones++;
                    System.out.println("  Encontrado en línea " + numeroLinea
                            + ", columna " + (columna + 1));
                    columna = line.indexOf(textoBuscado, columna + 1);
                }

                line = brf.readLine();
            }
        }

        if (totalApariciones == 0) {
            System.out.println("  No se ha encontrado el texto en este fichero.");
        } else {
            System.out.println("  Total de apariciones: " + totalApariciones);
        }
        System.out.println();
    }
}