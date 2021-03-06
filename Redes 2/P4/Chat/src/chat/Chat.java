package chat;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;


public class Chat extends javax.swing.JFrame {
    private String user;
    Client cliente;
    String[] conectados;
    String textoChat="";
    List<String> listaConectados;
    String privateTo="";
    DefaultListModel modelo;

    public Chat() {
        initComponents();
        txtChat.setContentType("text/html");
        txtChat.setEditable(false);
        txtChat.setText("");
        listaConectados=new ArrayList<>();
       
        cliente = new Client(this);
        user = JOptionPane.showInputDialog(this, "Nombre");
        if(user==null)
            System.exit(0);
        cliente.start();
        cliente.envia("<inicio> "+user);
        cliente.envia("<msg> <b>"+user+"</b> se ha conectado.");
        txtEstado.setText("Usuario "+user);
        
        listConectados.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt) {
                JList list =(JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    privateTo = (String)modelo.getElementAt(index);
                    String texto = JOptionPane.showInputDialog("Mensaje privado a " + privateTo);
                    
                    if(texto!=null)
                        cliente.envia("<privado><"+user+"><"+privateTo+"> "+texto);
                } else if (evt.getClickCount() == 3) {   // Triple-click
                    int index = list.locationToIndex(evt.getPoint());
                    privateTo = (String)modelo.getElementAt(index);
                }
            }
        });
    }

    public void updateUsersList(String usersCSV){
        String[] users = usersCSV.split(",");
        modelo = new DefaultListModel();
        listConectados.removeAll();
        
        for(String user:users){
            user.replace(" ","");
            user.trim();
            if(!user.contains("Conectados") || user.isEmpty())
                modelo.addElement(user);
        }
        listConectados.setModel(modelo);
    }
    public void Agregar_conversacion(String msg){
        String senderUser="";
        String toUser="";
        System.out.println(msg);
              
        msg = msg.replaceAll(":D", "<img width=\"50\" height=\"50\" src=\"file:./feliz.png\"></img>");
        msg = msg.replaceAll("<3", "<img width=\"50\" height=\"50\" src=\"file:./enamorado.png\"></img>");       
        msg = msg.replaceAll(":c", "<img width=\"50\" height=\"50\" src=\"file:./llorar.png\"></img>");
        msg = msg.replaceAll(":3", "<img width=\"50\" height=\"50\" src=\"file:./tierna.png\"></img>");
       
        if(msg.contains("<msg>")){
            senderUser=msg.split("<")[2].split(">")[0];
            if(senderUser.equals("b"))
                textoChat=textoChat+"<br/><br/>"+msg;
            else
                textoChat=textoChat+"<br/><br/><b>"+senderUser+"</b>:<br/> "+msg;
            txtChat.setText(textoChat);
        }else if(msg.contains("<inicio>")){
            msg.replace("<inicio>", "");
            listaConectados.add(msg);
        }
        else if(msg.contains("<conectados")){
            updateUsersList(msg);
        }else if(msg.contains("privado")){
            senderUser=msg.split("<")[2].replace(">", "");
            toUser=msg.split("<")[3].split(">")[0];
            if(toUser.equals(user))
            {
               textoChat=textoChat+"<br/><br/><b>Mensaje privado de "+senderUser+"</b>:<br/>"+msg;
               txtChat.setText(textoChat);
            }
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

        jScrollPane2 = new javax.swing.JScrollPane();
        listConectados = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtEnvia = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        txtEstado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        listConectados.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane2.setViewportView(listConectados);

        txtEnvia.setBackground(new java.awt.Color(51, 51, 51));
        txtEnvia.setColumns(20);
        txtEnvia.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        txtEnvia.setForeground(new java.awt.Color(255, 255, 255));
        txtEnvia.setRows(5);
        jScrollPane3.setViewportView(txtEnvia);

        btnEnviar.setBackground(new java.awt.Color(0, 51, 255));
        btnEnviar.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        btnEnviar.setForeground(new java.awt.Color(51, 51, 51));
        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        txtEstado.setText("Estado: Desconectado");

        jScrollPane1.setViewportView(txtChat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(txtEstado)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEstado)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        // TODO add your handling code here:

        String texto=txtEnvia.getText();
        cliente.envia("<msg><"+user+">"+texto);
        txtEnvia.setText("");
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        //cliente.envia("<fin>"+user);
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        cliente.envia("<fin>"+user);
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing


    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList listConectados;
    private javax.swing.JEditorPane txtChat;
    private javax.swing.JTextArea txtEnvia;
    private javax.swing.JLabel txtEstado;
    // End of variables declaration//GEN-END:variables
}

