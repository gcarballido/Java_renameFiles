/* Aplicacion grafica para renombrar numericamente
   todos los archivos contenidos en una carpeta
   a partir de una ruta y nombre dado
*/

package renamefilesapp;

// Importamos librerias
import java.awt.Font;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RenameFilesApp extends JFrame {
    
    JLabel rt_titulo;
    JLabel rt_ruta;
    JTextField cmp_ruta;
    JLabel rt_nombre;
    JTextField cmp_nombre;
    JButton btn_renombrar;
    JLabel tx_confirmacion;
    JPanel panel;

    // CONSTRUCTOR
    public RenameFilesApp(){
        // Panel
        panel = new JPanel();
        panel.setBounds(0,0,512,512);
        
        // Titulo
        rt_titulo = new JLabel("RENOMBRAR ARCHIVOS");
        rt_titulo.setFont(rt_titulo.getFont().deriveFont(16.0f));
        panel.add(rt_titulo);
        
        // Escribe la ruta a la carpeta
        rt_ruta = new JLabel("Ruta de la carpeta con los archivos:");
        rt_ruta.setBounds(30,40,450,20);
        rt_ruta.setFont(rt_ruta.getFont().deriveFont(14.0f));
        add(rt_ruta);
        
        // Campo de texto de la ruta
        cmp_ruta = new JTextField();
        cmp_ruta.setBounds(30,70,450,20);
        cmp_ruta.setText("C://Users//MyUser//MyFolder//");
        add(cmp_ruta);
        
        // Escribe el nombre nuevo de los archivos
        rt_nombre = new JLabel("Nombre nuevo:");
        rt_nombre.setBounds(30,100,450,20);
        rt_nombre.setFont(rt_nombre.getFont().deriveFont(14.0f));
        add(rt_nombre);
        
        // Campo de texto de la ruta
        cmp_nombre = new JTextField();
        cmp_nombre.setBounds(30,130,450,20);
        add(cmp_nombre);
        
        //Boton Renombrar
        btn_renombrar = new JButton("Renombrar");
        btn_renombrar.setBounds(30,170,100,20);
        add(btn_renombrar);
        btn_renombrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRenombrarActionPerformed(evt);
            }
        });
        
        // Mensaje de confirmacion
        tx_confirmacion = new JLabel("Introducir ruta y nombre nuevo");
        tx_confirmacion.setBounds(60,220,450,20);
        Font myFont = new Font("Serif", Font.ITALIC, 14);
        tx_confirmacion.setFont(myFont);
        add(tx_confirmacion);
        
        //Metemos el panel
        add(panel);
        
        //Parar la ejecucion al cerrar ventana
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    
    // BOTON Renombrar
    private void botonRenombrarActionPerformed(java.awt.event.ActionEvent evt) {
        
        try {
            String ruta = cmp_ruta.getText();                // Ruta a la carpeta con los archivos
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
                    String nombrenuevo = cmp_nombre.getText() + "_" + numero + formato; //NOMBRE NUEVO DEL ARCHIVO
                    String rutanueva = ruta + nombrenuevo;
                    File archivonuevo = new File(rutanueva);

                    //Renombramos los archivos
                    archivo.renameTo(archivonuevo);
                }
                numero++;                                                           // Incrementamos el contador
            }

            // Mensaje de finalizacion
            tx_confirmacion.setText("Renombrado finalizado");
            
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            tx_confirmacion.setText("La ruta o el nombre no son validos");
        }
    }
    
    // Clase MAIN
    public static void main(String[] args) {
        RenameFilesApp ventana = new RenameFilesApp();
        ventana.setBounds(50, 50, 512, 512);
        ventana.setVisible(true);
    }
}
