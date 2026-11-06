import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaApplication3 {

    public static void main(String[] args) throws IOException {

        String ruta = System.getProperty("user.dir");
        if (args.length > 0) ruta = args[0];

        File fich = new File(ruta);

        if (!fich.exists()) {
            System.out.println("No existe el fichero o directorio " + ruta);
        } else {
            if (fich.isFile()) {
                mostrarInformacion(fich);
                contarVocalesFileReader(fich);
                contarVocalesBufferedReader(fich);
            } else {
                System.out.println(" " + ruta + " es un directorio. Contenidos:");
                File[] ficheros = fich.listFiles();
                if (ficheros != null) {
                    for (File f : ficheros) {
                        String texto = f.isDirectory() ? "/" : f.isFile() ? "_" : "?";
                        System.out.println(texto + " " + f.getName() + "  (" + f.length() + " bytes)");

                        mostrarInformacion(f);

                        if (f.isFile()) {
                            contarVocalesFileReader(f);
                            contarVocalesBufferedReader(f);
                        }
                    }
                } else {
                    System.out.println("  (no se pudo leer el contenido, puede que falten permisos)");
                }
            }
        }
    }

    public static void mostrarInformacion(File f) throws IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.println("===============================================");
        System.out.println("INFORMACIÓN DE: " + f.getPath());
        System.out.println("===============================================");

        System.out.println("Nombre (getName)          : " + f.getName());
        System.out.println("Ruta absoluta (getAbsolutePath): " + f.getAbsolutePath());
        System.out.println("¿Es fichero? (isFile)       : " + f.isFile());
        System.out.println("¿Es directorio? (isDirectory): " + f.isDirectory());
        System.out.println("¿Se puede leer? (canRead)   : " + f.canRead());
        System.out.println("Tamaño en bytes (length)    : " + f.length());

        long fechaMod = f.lastModified();
        System.out.println("Última modificación : " + sdf.format(new Date(fechaMod)));
    }

    public static void contarVocalesFileReader(File f) throws IOException {

        int[] contador = new int[5];
        // TRY WITH RESOURCES
        // LIBERA OBJETOS AUTOMATICAMENTE
        try( FileReader fil = new FileReader(f); )
        {
            int letra = fil.read();
            while (letra != -1) {
                char c = Character.toLowerCase((char) letra);
                switch (c) {
                    case 'a': contador[0]++; break;
                    case 'e': contador[1]++; break;
                    case 'i': contador[2]++; break;
                    case 'o': contador[3]++; break;
                    case 'u': contador[4]++; break;
                }
                letra = fil.read();
            }
        }
        catch( Exception ex)
        {
            ex = ex;
        }
        // fil.close();

        System.out.println("--- Conteo de vocales (FileReader) ---");
        mostrarConteo(contador);
    }

    public static void contarVocalesBufferedReader(File f) throws IOException {

        int[] contador = new int[5]; // posiciones: a, e, i, o, u

        // ToDo :: TRY - WITH - RESOURCES
        FileReader fil = new FileReader(f);
        BufferedReader brf = new BufferedReader(fil);

        String line = brf.readLine();

        while (line != null) {

            String linea = line.toLowerCase();

            for (int i = 0; i < linea.length(); i++) {
                char c = linea.charAt(i);
                switch (c) {
                    case 'a': contador[0]++; break;
                    case 'e': contador[1]++; break;
                    case 'i': contador[2]++; break;
                    case 'o': contador[3]++; break;
                    case 'u': contador[4]++; break;
                }
            }

            line = brf.readLine();
        }

        brf.close();

        System.out.println("--- Conteo de vocales (BufferedReader) ---");
        mostrarConteo(contador);
        System.out.println();
    }

    public static void mostrarConteo(int[] contador) {
        System.out.println("  a: " + contador[0]);
        System.out.println("  e: " + contador[1]);
        System.out.println("  i: " + contador[2]);
        System.out.println("  o: " + contador[3]);
        System.out.println("  u: " + contador[4]);
        int total = contador[0] + contador[1] + contador[2] + contador[3] + contador[4];
        System.out.println("  TOTAL vocales: " + total);
    }
}
