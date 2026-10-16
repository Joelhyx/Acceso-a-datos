import java.io.File;
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
            mostrarInformacion(fich);

            if (fich.isFile()) {
                System.out.println(ruta + " es un fichero.");
            } else {
                System.out.println(" " + ruta + " es un directorio. Contenidos:");
                File[] ficheros = fich.listFiles();
                if (ficheros != null) {
                    for (File f : ficheros) {
                        String texto = f.isDirectory() ? "/" : f.isFile() ? "_" : "?";
                        System.out.println(texto + " " + f.getName()
                                + "  (" + f.length() + " bytes)");
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
        System.out.println("Ruta (getPath)            : " + f.getPath());
        System.out.println("Ruta absoluta (getAbsolutePath): " + f.getAbsolutePath());
        System.out.println("Ruta canónica (getCanonicalPath): " + f.getCanonicalPath());
        System.out.println("Directorio padre (getParent)   : " + f.getParent());
        File padreAbs = f.getParentFile();
        System.out.println("Padre absoluto (getParentFile)  : " + (padreAbs != null ? padreAbs.getAbsolutePath() : "null"));

        System.out.println("¿Es fichero? (isFile)       : " + f.isFile());
        System.out.println("¿Es directorio? (isDirectory): " + f.isDirectory());
        System.out.println("¿Es ruta absoluta? (isAbsolute): " + f.isAbsolute());
        System.out.println("¿Es oculto? (isHidden)      : " + f.isHidden());

        System.out.println("¿Existe? (exists)          : " + f.exists());
        System.out.println("¿Se puede leer? (canRead)   : " + f.canRead());
        System.out.println("¿Se puede escribir? (canWrite): " + f.canWrite());
        System.out.println("¿Se puede ejecutar? (canExecute): " + f.canExecute());

        System.out.println("Tamaño en bytes (length)    : " + f.length());
        long fechaMod = f.lastModified();
        System.out.println("Última modificación (lastModified): " + sdf.format(new Date(fechaMod)));

        System.out.println("Espacio total (getTotalSpace)    : " + f.getTotalSpace() + " bytes");
        System.out.println("Espacio libre (getFreeSpace)     : " + f.getFreeSpace() + " bytes");
        System.out.println("Espacio utilizable (getUsableSpace): " + f.getUsableSpace() + " bytes");

        File otro = new File(f.getAbsolutePath());
        System.out.println("¿Es igual a sí mismo? (equals)   : " + f.equals(otro));
        System.out.println("Comparación (compareTo)          : " + f.compareTo(otro));
        System.out.println("hashCode                         : " + f.hashCode());

        System.out.println("URI (toURI)                : " + f.toURI());

        System.out.println();
    }
}