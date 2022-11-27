package main;

import java.util.concurrent.CountDownLatch;

public class ConnectGUI extends javax.swing.JFrame {

    private Client client;
    /**
     * Creates new form ConnectGUI
     * @param client to connect with
     */
    public ConnectGUI(Client client) {
        this.client = client;
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
            java.util.logging.Logger.getLogger(AccountInfoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountInfoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountInfoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountInfoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setupConnectPanel() {
        setVisible(true);
        setLocationRelativeTo(null);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelConnect = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtIpAddress = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPortNumber = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe Script", 3, 36)); // NOI18N
        jLabel1.setText("Connect");

        jLabel2.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel2.setText("IP Address");

        txtIpAddress.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel3.setText("Port Number");

        txtPortNumber.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N

        btnConnect.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
                String ip = txtIpAddress.getText();
                int portNumber = Integer.valueOf(txtPortNumber.getText());
                try {

                    client = new Client(ip, portNumber);
                    setVisible(false);
                    dispose();
                    System.out.println("Client ip = " + client.socket);

                    RegisterOrLoginGUI window = new RegisterOrLoginGUI(client);
                    window.getRegisterFrame().setLocationRelativeTo(null); // center on screen
                    window.getRegisterFrame().setVisible(true); // make visible

                    /*
                    LoginGUI window = new LoginGUI(client);
             		window.getLoginFrame().setLocationRelativeTo(null); // center on screen
             		window.getLoginFrame().setVisible(true); // make visible
             		*/
                }
                catch(Exception e) {

                }

            }
        });

        javax.swing.GroupLayout panelConnectLayout = new javax.swing.GroupLayout(panelConnect);
        panelConnect.setLayout(panelConnectLayout);
        panelConnectLayout.setHorizontalGroup(
                panelConnectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelConnectLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSeparator1)
                                .addContainerGap())
                        .addGroup(panelConnectLayout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addGroup(panelConnectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelConnectLayout.createSequentialGroup()
                                                .addGroup(panelConnectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3))
                                                .addGap(28, 28, 28)
                                                .addGroup(panelConnectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txtIpAddress)
                                                        .addComponent(txtPortNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(118, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConnectLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(137, 137, 137))
        );
        panelConnectLayout.setVerticalGroup(
                panelConnectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelConnectLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addGroup(panelConnectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtIpAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelConnectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtPortNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addComponent(btnConnect)
                                .addContainerGap(142, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        /*
    	setVisible(false);
        dispose();
        System.out.println("Client ip = " + client.socket);
        LoginGUI window = new LoginGUI(client);
		window.getLoginFrame().setLocationRelativeTo(null); // center on screen
		window.getLoginFrame().setVisible(true); // make visible
		*/
        //new LobbyGUI(client).setupLobbyPanel();      
    }//GEN-LAST:event_btnConnectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        //String ip = "127.0.1.1";
        //int port = 59898;
        Client client = new Client();
        client.setPlayer(new Player("Samira", 1000));
        new ConnectGUI(client).setupConnectPanel();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panelConnect;
    private javax.swing.JTextField txtIpAddress;
    private javax.swing.JTextField txtPortNumber;
    // End of variables declaration//GEN-END:variables
}