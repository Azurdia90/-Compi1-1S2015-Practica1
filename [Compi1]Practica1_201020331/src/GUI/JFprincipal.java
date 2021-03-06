package GUI;

import Analizadores.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * @author Cristian
 * CLASE QUE MANEJA LA VENTANA PRINCIPAL 
 */

public class JFprincipal extends javax.swing.JFrame {
    
    private JFileChooser manejo_archivos;
    private File archivo;
    
    public JFprincipal() {
        initComponents();
    }
    
    public void manejar_archivo(){
        manejo_archivos = new JFileChooser();
        int resultado = 0;
        resultado = manejo_archivos.showOpenDialog(null);
        if(resultado == manejo_archivos.CANCEL_OPTION){
            archivo = null;
        }else if(resultado == manejo_archivos.APPROVE_OPTION){
            archivo = manejo_archivos.getSelectedFile().getAbsoluteFile();
        }      
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTAarchivo = new javax.swing.JTextArea();
        jMBanalizador = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMIabrir = new javax.swing.JMenuItem();
        jMInuevo = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jManalizar = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTAarchivo.setColumns(20);
        jTAarchivo.setRows(5);
        jScrollPane1.setViewportView(jTAarchivo);

        jMenu2.setText("Archivo");

        jMIabrir.setText("Abrir");
        jMIabrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIabrirActionPerformed(evt);
            }
        });
        jMenu2.add(jMIabrir);

        jMInuevo.setText("Nuevo");
        jMInuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMInuevoActionPerformed(evt);
            }
        });
        jMenu2.add(jMInuevo);

        jMenuItem3.setText("Guardar");
        jMenu2.add(jMenuItem3);

        jMBanalizador.add(jMenu2);

        jManalizar.setText("Analizar");
        jManalizar.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jManalizarMenuSelected(evt);
            }
        });
        jManalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jManalizarActionPerformed(evt);
            }
        });
        jMBanalizador.add(jManalizar);

        jMenu5.setText("Errores");
        jMBanalizador.add(jMenu5);

        setJMenuBar(jMBanalizador);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMIabrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIabrirActionPerformed
        manejar_archivo();
        if(archivo.exists()){ //verificamos que el archivo abierto existe
            try{
                BufferedReader leer = new BufferedReader(new FileReader(archivo));
                StringBuffer leer_cadena = new StringBuffer();
                String cadena_entrada = null;
                jTAarchivo.setText("");
                while((cadena_entrada = leer.readLine()) != null ){
                    leer_cadena.append(cadena_entrada + "\n");
                }
                jTAarchivo.setText(leer_cadena.toString());
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "error al cargar archivo");
            }
        }else{ //de lo contrario desplegamos un dialogo adviertiendo
            JOptionPane.showMessageDialog(null, "archivo no existe");
        }
    }//GEN-LAST:event_jMIabrirActionPerformed

    private void jMInuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMInuevoActionPerformed
        jTAarchivo.setText("");
    }//GEN-LAST:event_jMInuevoActionPerformed

    private void jManalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jManalizarActionPerformed
        

    }//GEN-LAST:event_jManalizarActionPerformed

    private void jManalizarMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jManalizarMenuSelected
        System.out.println("--------------INICIO DE COMPILACIÓN-----------------"); 
        try { 
            new sintactico(new lexico( new StringReader(jTAarchivo.getText()))).parse();
        } catch (Exception ex) { 
            Logger.getLogger(JFprincipal.class.getName()).log(Level.SEVERE, null, ex); 
        }//fin catch 
        System.out.println("----------------FIN DE COMPILACIÓN-----------------");
    }//GEN-LAST:event_jManalizarMenuSelected


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMBanalizador;
    private javax.swing.JMenuItem jMIabrir;
    private javax.swing.JMenuItem jMInuevo;
    private javax.swing.JMenu jManalizar;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTAarchivo;
    // End of variables declaration//GEN-END:variables
}
