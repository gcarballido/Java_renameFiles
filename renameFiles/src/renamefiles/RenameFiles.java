
package renamefiles;

// Importamos librerias
import java.io.File;

// Programa para renombrar archivos
public class RenameFiles {
    
    // Clase main
    public static void main(String[] args) {
        String ruta = "C://Users//MyUser//MyFolder//";                // Ruta a la carpeta con los archivos
        File carpeta = new File(ruta);
        File[] listadeArchivos = carpeta.listFiles();                           // Metemos en un array todos los archivos
        
        int numero;
        numero = 1;                                                             // Creamos un contador de archivos
        
        for (File archivo : listadeArchivos) {                                  // Recorremos la matriz y si es un archivo cogemos el nombre
            if (archivo.isFile()) {
                // Cogemos el formato del archivo para mantenerlo
                String nombreviejo = archivo.getName();
                String formato = nombreviejo.substring(nombreviejo.lastIndexOf("."));
                
                // Creamos el nuevo nombre (para java hay que hacer un nuevo archivo)
                String nombrenuevo = "NuevoNombre" + numero + formato;                          
                String rutanueva = ruta + nombrenuevo;
                File archivonuevo = new File(rutanueva);
                
                //Renombramos los archivos
                archivo.renameTo(archivonuevo);
            }
            numero++;                                                           // Incrementamos el contador
        }
    }
    
}
