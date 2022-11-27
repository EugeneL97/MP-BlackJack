package main;

public class AccountInfoGUI extends javax.swing.JFrame {

    private Client client;

    /**
     * Creates new form AccountInfoGUI
     *
     * @param client to connect with
     */
    public AccountInfoGUI(Client client) {
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

    public void setupAccountInfoPanel() {
        try {
            txtUserName.setText(client.getPlayer().getUsername());
            txtAccountBalance.setText(String.valueOf(client.getPlayer().getAccountBalance()));
        } catch (Exception ex) {

        }
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAccountInfo = new javax.swing.JPanel();
        btnDeposit = new javax.swing.JButton();
        btnWithdraw = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtAccountBalance = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtAccount = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panelAccountInfo.setPreferredSize(new java.awt.Dimension(500, 500));

        btnDeposit.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        btnDeposit.setText("Deposit");
        btnDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositActionPerformed(evt);
            }
        });

        btnWithdraw.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        btnWithdraw.setText("Withdraw");
        btnWithdraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWithdrawActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Account Balance");

        txtAccountBalance.setEditable(false);
        txtAccountBalance.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe Script", 3, 36)); // NOI18N
        jLabel2.setText("Account Info");

        jLabel5.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("User Name");

        txtUserName.setEditable(false);
        txtUserName.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel6.setText("Enter Amount To Deposit/Withdraw");

        txtAccount.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jButton1.setText("Go to Lobby");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAccountInfoLayout = new javax.swing.GroupLayout(panelAccountInfo);
        panelAccountInfo.setLayout(panelAccountInfoLayout);
        panelAccountInfoLayout.setHorizontalGroup(
                panelAccountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAccountInfoLayout.createSequentialGroup()
                                .addGroup(panelAccountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelAccountInfoLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jSeparator1))
                                        .addGroup(panelAccountInfoLayout.createSequentialGroup()
                                                .addGroup(panelAccountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(panelAccountInfoLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(panelAccountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addGroup(panelAccountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelAccountInfoLayout.createSequentialGroup()
                                                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGap(18, 18, 18)
                                                                                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(panelAccountInfoLayout.createSequentialGroup()
                                                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGap(18, 18, 18)
                                                                                        .addComponent(txtAccountBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                                                                        .addGroup(panelAccountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(txtAccount)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAccountInfoLayout.createSequentialGroup()
                                                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(panelAccountInfoLayout.createSequentialGroup()
                                                                                        .addGap(25, 25, 25)
                                                                                        .addComponent(btnDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGap(18, 18, 18)
                                                                                        .addComponent(btnWithdraw, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                                                        .addComponent(jSeparator3)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelAccountInfoLayout.createSequentialGroup()
                                                                .addGroup(panelAccountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelAccountInfoLayout.createSequentialGroup()
                                                                                .addGap(100, 100, 100)
                                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelAccountInfoLayout.createSequentialGroup()
                                                                                .addGap(161, 161, 161)
                                                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addGap(0, 109, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        panelAccountInfoLayout.setVerticalGroup(
                panelAccountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAccountInfoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelAccountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelAccountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtAccountBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAccountInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnDeposit)
                                        .addComponent(btnWithdraw))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addContainerGap(122, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelAccountInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelAccountInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositActionPerformed
        try {
            int amount = Integer.valueOf(txtAccount.getText().trim());
            if (amount >= 0) {
                client.getPlayer().setAccountBalance(client.getPlayer().getAccountBalance() + amount);
                txtAccountBalance.setText(String.valueOf(client.getPlayer().getAccountBalance()));
                txtAccount.setText("");
            }
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnDepositActionPerformed

    private void btnWithdrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWithdrawActionPerformed
        try {
            int amount = Integer.valueOf(txtAccount.getText().trim());
            if (amount >= 0 && amount <= client.getPlayer().getAccountBalance()) {
                client.getPlayer().setAccountBalance(client.getPlayer().getAccountBalance() - amount);
                txtAccountBalance.setText(String.valueOf(client.getPlayer().getAccountBalance()));
                txtAccount.setText("");
            }
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnWithdrawActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setVisible(false);
                    dispose();
                    new LobbyGUI(client).setupLobbyPanel();
                }
            });
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Client client = new Client();
                    client.setPlayer(new Player("Samira", 1000));
                    new AccountInfoGUI(client).setupAccountInfoPanel();
                } catch(Exception ex) {}
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeposit;
    private javax.swing.JButton btnWithdraw;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel panelAccountInfo;
    private javax.swing.JTextField txtAccount;
    private javax.swing.JTextField txtAccountBalance;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}