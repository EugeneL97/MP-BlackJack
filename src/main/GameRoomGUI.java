package main;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Sat Nov 26 02:17:29 PST 2022
 */



/**
 * @author unknown
 */
public class GameRoomGUI extends JFrame {

    private Client client;

    public GameRoomGUI() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        panel3 = new JPanel();
        textareaPlayer0 = new JTextArea();
        textArea6 = new JTextArea();
        textAreaPlayer4 = new JTextArea();
        button9 = new JButton();
        button10 = new JButton();
        textAreaPlayer1 = new JTextArea();
        textAreaPlayer2 = new JTextArea();
        textAreaPlayer3 = new JTextArea();
        button11 = new JButton();
        button13 = new JButton();
        button12 = new JButton();
        button3 = new JButton();
        button5 = new JButton();
        button4 = new JButton();
        button6 = new JButton();
        button1 = new JButton();
        button2 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        textField1 = new JTextField();
        lblFunds2 = new JLabel();
        textField3 = new JTextField();
        lblFunds4 = new JLabel();
        textField5 = new JTextField();
        lblFunds6 = new JLabel();
        textField2 = new JTextField();
        lblFunds3 = new JLabel();
        textField4 = new JTextField();
        lblFunds5 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("Game Room");
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setFont(new Font("Roboto", Font.BOLD | Font.ITALIC, 36));
            panel1.add(label1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
        }

        //======== panel3 ========
        {

            //---- textareaPlayer0 ----
            textareaPlayer0.setPreferredSize(new Dimension(200, 200));
            textareaPlayer0.setBorder(new TitledBorder(null, "Player 1", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));

            //---- textArea6 ----
            textArea6.setPreferredSize(new Dimension(200, 200));
            textArea6.setBorder(new TitledBorder(null, "Dealer", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));

            //---- textAreaPlayer4 ----
            textAreaPlayer4.setPreferredSize(new Dimension(200, 200));
            textAreaPlayer4.setBorder(new TitledBorder(null, "Player 5", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));

            //---- button9 ----
            button9.setText("Take a Seat");
            button9.setMinimumSize(new Dimension(200, 30));
            button9.setPreferredSize(new Dimension(200, 30));

            //---- button10 ----
            button10.setText("Take a Seat");
            button10.setMinimumSize(new Dimension(200, 30));
            button10.setPreferredSize(new Dimension(200, 30));

            //---- textAreaPlayer1 ----
            textAreaPlayer1.setPreferredSize(new Dimension(200, 200));
            textAreaPlayer1.setBorder(new TitledBorder(null, "Player 2", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));

            //---- textAreaPlayer2 ----
            textAreaPlayer2.setPreferredSize(new Dimension(200, 200));
            textAreaPlayer2.setBorder(new TitledBorder(null, "Player 3", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));

            //---- textAreaPlayer3 ----
            textAreaPlayer3.setPreferredSize(new Dimension(200, 200));
            textAreaPlayer3.setBorder(new TitledBorder(null, "Player 4", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));

            //---- button11 ----
            button11.setText("Take a Seat");
            button11.setMinimumSize(new Dimension(200, 30));
            button11.setPreferredSize(new Dimension(200, 30));

            //---- button13 ----
            button13.setText("Take a Seat");
            button13.setMinimumSize(new Dimension(200, 30));
            button13.setPreferredSize(new Dimension(200, 30));

            //---- button12 ----
            button12.setText("Take a Seat");
            button12.setMinimumSize(new Dimension(200, 30));
            button12.setPreferredSize(new Dimension(200, 30));

            //---- button3 ----
            button3.setText("Double");

            //---- button5 ----
            button5.setText("Bet 50");

            //---- button4 ----
            button4.setText("Bet 100");

            //---- button6 ----
            button6.setText("Bet 500");

            //---- button1 ----
            button1.setText("Leave Room");

            //---- button2 ----
            button2.setText("Sit Out");

            //---- button7 ----
            button7.setText("Hit");

            //---- button8 ----
            button8.setText("Deal");

            //---- lblFunds2 ----
            lblFunds2.setText("Funds");

            //---- lblFunds4 ----
            lblFunds4.setText("Funds");

            //---- lblFunds6 ----
            lblFunds6.setText("Funds");

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGroup(panel3Layout.createParallelGroup()
                                            .addComponent(button11, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(button2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                                .addComponent(button1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
                                        .addGroup(panel3Layout.createParallelGroup()
                                            .addGroup(panel3Layout.createSequentialGroup()
                                                .addGap(118, 118, 118)
                                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(button3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(panel3Layout.createSequentialGroup()
                                                        .addComponent(button5, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(button4, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(button6, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(panel3Layout.createSequentialGroup()
                                                .addGap(130, 130, 130)
                                                .addGroup(panel3Layout.createParallelGroup()
                                                    .addComponent(textArea6, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(button13, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(textAreaPlayer2, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(panel3Layout.createSequentialGroup()
                                                        .addComponent(lblFunds6)
                                                        .addGap(6, 6, 6)
                                                        .addComponent(textField5, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(137, 137, 137)
                                        .addComponent(button12, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(textareaPlayer0, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textAreaPlayer1, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(GroupLayout.Alignment.LEADING, panel3Layout.createSequentialGroup()
                                                .addComponent(lblFunds2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(button9, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
                                        .addGap(508, 508, 508)
                                        .addGroup(panel3Layout.createParallelGroup()
                                            .addComponent(textAreaPlayer3, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textAreaPlayer4, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(button10, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panel3Layout.createSequentialGroup()
                                                .addComponent(lblFunds4)
                                                .addGap(6, 6, 6)
                                                .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(358, 358, 358)
                                .addComponent(button7, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button8, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(21, Short.MAX_VALUE))
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textAreaPlayer4, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textArea6, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textareaPlayer0, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(button10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblFunds2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGroup(panel3Layout.createParallelGroup()
                                            .addComponent(lblFunds4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addComponent(textAreaPlayer1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textAreaPlayer3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textAreaPlayer2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(button11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(button13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(button12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addComponent(button7, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                    .addComponent(button8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(button3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button5)
                                    .addComponent(button6)
                                    .addComponent(button4)))
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addComponent(lblFunds6, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 312, Short.MAX_VALUE)))
                        .addContainerGap())
            );
        }

        //---- lblFunds3 ----
        lblFunds3.setText("Funds");

        //---- lblFunds5 ----
        lblFunds5.setText("Funds");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)
                        .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(lblFunds3)
                    .addGap(6, 6, 6)
                    .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 510, Short.MAX_VALUE)
                    .addComponent(lblFunds5)
                    .addGap(6, 6, 6)
                    .addComponent(textField4, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(lblFunds3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFunds5, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public void setupGameRoom(Client client) {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.client = client;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JPanel panel3;
    private JTextArea textareaPlayer0;
    private JTextArea textArea6;
    private JTextArea textAreaPlayer4;
    private JButton button9;
    private JButton button10;
    private JTextArea textAreaPlayer1;
    private JTextArea textAreaPlayer2;
    private JTextArea textAreaPlayer3;
    private JButton button11;
    private JButton button13;
    private JButton button12;
    private JButton button3;
    private JButton button5;
    private JButton button4;
    private JButton button6;
    private JButton button1;
    private JButton button2;
    private JButton button7;
    private JButton button8;
    private JTextField textField1;
    private JLabel lblFunds2;
    private JTextField textField3;
    private JLabel lblFunds4;
    private JTextField textField5;
    private JLabel lblFunds6;
    private JTextField textField2;
    private JLabel lblFunds3;
    private JTextField textField4;
    private JLabel lblFunds5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:ond
}
