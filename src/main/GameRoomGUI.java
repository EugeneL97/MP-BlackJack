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

    // GUI variables
    private JPanel panelGameRoomTitle;
    private JLabel lblTitle;
    private JPanel panelGameRoom;
    private JTextArea textAreaPlayer1;
    private JTextField player1Wager;
    private JLabel lblPlayer1Wager;
    private JButton btnTakeSeat1;
    private JButton btnTakeSeat2;
    private JTextArea textAreaPlayer2;
    private JTextField player2Wager;
    private JLabel lblPlayer2Wager;
    private JButton btnTakeSeat3;
    private JTextArea textAreaPlayer3;
    private JTextField player3Wager;
    private JLabel lblPlayer3Wager;
    private JButton btnTakeSeat4;
    private JTextArea textAreaPlayer4;
    private JTextField player4Wager;
    private JLabel lblPlayer4Wager;
    private JButton btnTakeSeat5;
    private JTextArea textAreaPlayer5;
    private JTextField player5Wager;
    private JLabel lblPlayer5Wager;
    private JTextArea textAreaPlayer6;
    private JPanel panelGameRoomButtons;
    private JButton btnSitOut;
    private JButton btnLeaveRoom;
    private JButton btnBet50;
    private JButton btnBet100;
    private JButton btnBet500;
    private JButton btnDouble;
    private JButton btnHit;
    private JButton btnDeal;

    // Client variable
    private Client client;

    public GameRoomGUI(Client client) {
        this.client = client;

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {}

        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panelGameRoomTitle = new JPanel();
        lblTitle = new JLabel();
        panelGameRoom = new JPanel();
        textAreaPlayer1 = new JTextArea();
        player1Wager = new JTextField();
        lblPlayer1Wager = new JLabel();
        btnTakeSeat1 = new JButton();
        btnTakeSeat2 = new JButton();
        textAreaPlayer2 = new JTextArea();
        player2Wager = new JTextField();
        lblPlayer2Wager = new JLabel();
        btnTakeSeat3 = new JButton();
        textAreaPlayer3 = new JTextArea();
        player3Wager = new JTextField();
        lblPlayer3Wager = new JLabel();
        btnTakeSeat4 = new JButton();
        textAreaPlayer4 = new JTextArea();
        player4Wager = new JTextField();
        lblPlayer4Wager = new JLabel();
        btnTakeSeat5 = new JButton();
        textAreaPlayer5 = new JTextArea();
        player5Wager = new JTextField();
        lblPlayer5Wager = new JLabel();
        textAreaPlayer6 = new JTextArea();
        panelGameRoomButtons = new JPanel();
        btnSitOut = new JButton();
        btnLeaveRoom = new JButton();
        btnBet50 = new JButton();
        btnBet100 = new JButton();
        btnBet500 = new JButton();
        btnDouble = new JButton();
        btnHit = new JButton();
        btnDeal = new JButton();

        //======== this ========
        setBackground(new Color(0x003300));
        setForeground(new Color(0x003300));
        var contentPane = getContentPane();

        //======== panelGameRoomTitle ========
        {
            panelGameRoomTitle.setBackground(new Color(0x003300));
            panelGameRoomTitle.setLayout(new GridBagLayout());
            ((GridBagLayout)panelGameRoomTitle.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panelGameRoomTitle.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //---- lblTitle ----
            lblTitle.setText("Game Room");
            lblTitle.setFont(new Font("Roboto", Font.BOLD | Font.ITALIC, 36));
            lblTitle.setForeground(Color.white);
            panelGameRoomTitle.add(lblTitle, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
        }

        //======== panelGameRoom ========
        {
            panelGameRoom.setBackground(new Color(0x003300));
            panelGameRoom.setForeground(Color.black);

            //---- textAreaPlayer1 ----
            textAreaPlayer1.setFont(new Font("Roboto", Font.PLAIN, 14));
            textAreaPlayer1.setBorder(new TitledBorder(null, "Player 1", TitledBorder.CENTER, TitledBorder.TOP,
                    new Font("Roboto", Font.BOLD, 14)));

            //---- player1Wager ----
            player1Wager.setFont(new Font("Roboto", Font.PLAIN, 14));

            //---- lblPlayer1Wager ----
            lblPlayer1Wager.setText("Wager");
            lblPlayer1Wager.setFont(new Font("Roboto", Font.BOLD, 16));
            lblPlayer1Wager.setPreferredSize(new Dimension(40, 17));
            lblPlayer1Wager.setHorizontalAlignment(SwingConstants.CENTER);
            lblPlayer1Wager.setForeground(Color.white);

            //---- btnTakeSeat1 ----
            btnTakeSeat1.setText("Take Seat");
            btnTakeSeat1.setFont(new Font("Roboto", Font.PLAIN, 12));

            //---- btnTakeSeat2 ----
            btnTakeSeat2.setText("Take Seat");
            btnTakeSeat2.setFont(new Font("Roboto", Font.PLAIN, 12));

            //---- textAreaPlayer2 ----
            textAreaPlayer2.setFont(new Font("Roboto", Font.PLAIN, 14));
            textAreaPlayer2.setBorder(new TitledBorder(null, "Player 2", TitledBorder.CENTER, TitledBorder.TOP,
                    new Font("Roboto", Font.BOLD, 14)));

            //---- player2Wager ----
            player2Wager.setFont(new Font("Roboto", Font.PLAIN, 14));

            //---- lblPlayer2Wager ----
            lblPlayer2Wager.setText("Wager");
            lblPlayer2Wager.setFont(new Font("Roboto", Font.BOLD, 16));
            lblPlayer2Wager.setPreferredSize(new Dimension(40, 17));
            lblPlayer2Wager.setHorizontalAlignment(SwingConstants.CENTER);
            lblPlayer2Wager.setForeground(Color.white);

            //---- btnTakeSeat3 ----
            btnTakeSeat3.setText("Take Seat");
            btnTakeSeat3.setFont(new Font("Roboto", Font.PLAIN, 12));

            //---- textAreaPlayer3 ----
            textAreaPlayer3.setFont(new Font("Roboto", Font.PLAIN, 14));
            textAreaPlayer3.setBorder(new TitledBorder(null, "Player 3", TitledBorder.CENTER, TitledBorder.TOP,
                    new Font("Roboto", Font.BOLD, 14)));

            //---- player3Wager ----
            player3Wager.setFont(new Font("Roboto", Font.PLAIN, 14));

            //---- lblPlayer3Wager ----
            lblPlayer3Wager.setText("Wager");
            lblPlayer3Wager.setFont(new Font("Roboto", Font.BOLD, 16));
            lblPlayer3Wager.setPreferredSize(new Dimension(40, 17));
            lblPlayer3Wager.setHorizontalAlignment(SwingConstants.CENTER);
            lblPlayer3Wager.setForeground(Color.white);

            //---- btnTakeSeat4 ----
            btnTakeSeat4.setText("Take Seat");
            btnTakeSeat4.setFont(new Font("Roboto", Font.PLAIN, 12));

            //---- textAreaPlayer4 ----
            textAreaPlayer4.setFont(new Font("Roboto", Font.PLAIN, 14));
            textAreaPlayer4.setBorder(new TitledBorder(null, "Player 4", TitledBorder.CENTER, TitledBorder.TOP,
                    new Font("Roboto", Font.BOLD, 14)));

            //---- player4Wager ----
            player4Wager.setFont(new Font("Roboto", Font.PLAIN, 14));

            //---- lblPlayer4Wager ----
            lblPlayer4Wager.setText("Wager");
            lblPlayer4Wager.setFont(new Font("Roboto", Font.BOLD, 16));
            lblPlayer4Wager.setPreferredSize(new Dimension(40, 17));
            lblPlayer4Wager.setHorizontalAlignment(SwingConstants.CENTER);
            lblPlayer4Wager.setForeground(Color.white);

            //---- btnTakeSeat5 ----
            btnTakeSeat5.setText("Take Seat");
            btnTakeSeat5.setFont(new Font("Roboto", Font.PLAIN, 12));

            //---- textAreaPlayer5 ----
            textAreaPlayer5.setFont(new Font("Roboto", Font.PLAIN, 14));
            textAreaPlayer5.setBorder(new TitledBorder(null, "Player 5", TitledBorder.CENTER, TitledBorder.TOP,
                    new Font("Roboto", Font.BOLD, 14)));

            //---- player5Wager ----
            player5Wager.setFont(new Font("Roboto", Font.PLAIN, 14));

            //---- lblPlayer5Wager ----
            lblPlayer5Wager.setText("Wager");
            lblPlayer5Wager.setFont(new Font("Roboto", Font.BOLD, 16));
            lblPlayer5Wager.setPreferredSize(new Dimension(40, 17));
            lblPlayer5Wager.setHorizontalAlignment(SwingConstants.CENTER);
            lblPlayer5Wager.setForeground(Color.white);

            //---- textAreaPlayer6 ----
            textAreaPlayer6.setFont(new Font("Roboto", Font.PLAIN, 14));
            textAreaPlayer6.setBorder(new TitledBorder(null, "Dealer", TitledBorder.CENTER, TitledBorder.TOP,
                    new Font("Roboto", Font.BOLD, 14)));

            GroupLayout panelGameRoomLayout = new GroupLayout(panelGameRoom);
            panelGameRoom.setLayout(panelGameRoomLayout);
            panelGameRoomLayout.setHorizontalGroup(
                    panelGameRoomLayout.createParallelGroup()
                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                    .addGroup(panelGameRoomLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addGroup(panelGameRoomLayout.createParallelGroup()
                                                    .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                            .addComponent(lblPlayer2Wager, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                            .addGap(6, 6, 6)
                                                            .addComponent(player2Wager, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(textAreaPlayer2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnTakeSeat2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                    .addComponent(lblPlayer1Wager, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(player1Wager, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(textAreaPlayer1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnTakeSeat1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                                    .addGroup(panelGameRoomLayout.createParallelGroup()
                                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                    .addComponent(lblPlayer3Wager, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(6, 6, 6)
                                                    .addComponent(player3Wager, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(textAreaPlayer3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnTakeSeat3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textAreaPlayer6, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                    .addGap(203, 203, 203)
                                    .addGroup(panelGameRoomLayout.createParallelGroup()
                                            .addGroup(GroupLayout.Alignment.TRAILING, panelGameRoomLayout.createParallelGroup()
                                                    .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                            .addComponent(lblPlayer4Wager, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                            .addGap(6, 6, 6)
                                                            .addComponent(player4Wager, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(textAreaPlayer4, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnTakeSeat4, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.TRAILING, panelGameRoomLayout.createParallelGroup()
                                                    .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                            .addComponent(lblPlayer5Wager, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                            .addGap(6, 6, 6)
                                                            .addComponent(player5Wager, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(textAreaPlayer5, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnTakeSeat5, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
            );
            panelGameRoomLayout.setVerticalGroup(
                    panelGameRoomLayout.createParallelGroup()
                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                    .addGroup(panelGameRoomLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                    .addGroup(panelGameRoomLayout.createParallelGroup()
                                                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                                    .addGroup(panelGameRoomLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(lblPlayer1Wager, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(player1Wager, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(textAreaPlayer1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnTakeSeat1))
                                                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                                    .addGroup(panelGameRoomLayout.createParallelGroup()
                                                                            .addComponent(lblPlayer5Wager, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                                                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                                                    .addGap(1, 1, 1)
                                                                                    .addComponent(player5Wager, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
                                                                    .addGap(6, 6, 6)
                                                                    .addComponent(textAreaPlayer5, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(6, 6, 6)
                                                                    .addComponent(btnTakeSeat5)))
                                                    .addGap(52, 52, 52)
                                                    .addGroup(panelGameRoomLayout.createParallelGroup()
                                                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                                    .addGroup(panelGameRoomLayout.createParallelGroup()
                                                                            .addComponent(lblPlayer2Wager, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                                                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                                                    .addGap(1, 1, 1)
                                                                                    .addComponent(player2Wager, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
                                                                    .addGap(6, 6, 6)
                                                                    .addComponent(textAreaPlayer2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(6, 6, 6)
                                                                    .addComponent(btnTakeSeat2))
                                                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                                    .addGap(2, 2, 2)
                                                                    .addGroup(panelGameRoomLayout.createParallelGroup()
                                                                            .addComponent(lblPlayer4Wager, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                                                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                                                    .addGap(1, 1, 1)
                                                                                    .addComponent(player4Wager, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
                                                                    .addGap(6, 6, 6)
                                                                    .addComponent(textAreaPlayer4, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(6, 6, 6)
                                                                    .addComponent(btnTakeSeat4))))
                                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                    .addComponent(textAreaPlayer6)
                                                    .addGap(53, 53, 53)
                                                    .addGroup(panelGameRoomLayout.createParallelGroup()
                                                            .addComponent(lblPlayer3Wager, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(panelGameRoomLayout.createSequentialGroup()
                                                                    .addGap(1, 1, 1)
                                                                    .addComponent(player3Wager, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
                                                    .addGap(6, 6, 6)
                                                    .addComponent(textAreaPlayer3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(6, 6, 6)
                                                    .addComponent(btnTakeSeat3)
                                                    .addGap(2, 2, 2)))
                                    .addContainerGap(61, Short.MAX_VALUE))
            );
        }

        //======== panelGameRoomButtons ========
        {
            panelGameRoomButtons.setBackground(new Color(0x003300));

            //---- btnSitOut ----
            btnSitOut.setText("Sit Out");
            btnSitOut.setFont(new Font("Roboto", Font.PLAIN, 12));

            //---- btnLeaveRoom ----
            btnLeaveRoom.setText("Leave Room");
            btnLeaveRoom.setFont(new Font("Roboto", Font.PLAIN, 12));
            btnLeaveRoom.addActionListener(e -> leaveRoom());

            //---- btnBet50 ----
            btnBet50.setText("Bet 50");
            btnBet50.setFont(new Font("Roboto", Font.PLAIN, 12));

            //---- btnBet100 ----
            btnBet100.setText("Bet 100");
            btnBet100.setFont(new Font("Roboto", Font.PLAIN, 12));

            //---- btnBet500 ----
            btnBet500.setText("Bet 500");
            btnBet500.setFont(new Font("Roboto", Font.PLAIN, 12));

            //---- btnDouble ----
            btnDouble.setText("Double Bet");
            btnDouble.setFont(new Font("Roboto", Font.PLAIN, 12));

            //---- btnHit ----
            btnHit.setText("HIT");
            btnHit.setFont(new Font("Roboto", Font.PLAIN, 12));

            //---- btnDeal ----
            btnDeal.setText("FINISH");
            btnDeal.setFont(new Font("Roboto", Font.PLAIN, 12));

            GroupLayout panelGameRoomButtonsLayout = new GroupLayout(panelGameRoomButtons);
            panelGameRoomButtons.setLayout(panelGameRoomButtonsLayout);
            panelGameRoomButtonsLayout.setHorizontalGroup(
                    panelGameRoomButtonsLayout.createParallelGroup()
                            .addGroup(panelGameRoomButtonsLayout.createSequentialGroup()
                                    .addGap(32, 32, 32)
                                    .addGroup(panelGameRoomButtonsLayout.createParallelGroup()
                                            .addComponent(btnSitOut, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                            .addComponent(btnLeaveRoom, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                    .addGap(180, 180, 180)
                                    .addGroup(panelGameRoomButtonsLayout.createParallelGroup()
                                            .addComponent(btnDouble, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(panelGameRoomButtonsLayout.createSequentialGroup()
                                                    .addComponent(btnBet50, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnBet100, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnBet500, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(btnHit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(181, 181, 181)
                                    .addComponent(btnDeal, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20))
            );
            panelGameRoomButtonsLayout.setVerticalGroup(
                    panelGameRoomButtonsLayout.createParallelGroup()
                            .addGroup(panelGameRoomButtonsLayout.createSequentialGroup()
                                    .addGroup(panelGameRoomButtonsLayout.createParallelGroup()
                                            .addGroup(panelGameRoomButtonsLayout.createSequentialGroup()
                                                    .addComponent(btnHit, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnDouble))
                                            .addGroup(panelGameRoomButtonsLayout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(btnDeal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelGameRoomButtonsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnBet500)
                                            .addComponent(btnBet100)
                                            .addComponent(btnBet50)))
                            .addGroup(panelGameRoomButtonsLayout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addComponent(btnSitOut, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnLeaveRoom, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(15, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(panelGameRoomTitle, GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
                        .addComponent(panelGameRoom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelGameRoomButtons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(panelGameRoomTitle, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(panelGameRoom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(panelGameRoomButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public void setupGameRoom() {
        setVisible(true);
    }

    public static void main(String agrs[]) throws Exception {
        new GameRoomGUI(new Client()).setupGameRoom();
    }

    public void sit() {

    }

    public void leaveRoom() {
        client.leaveRoom();
        setVisible(false);
        dispose();
        new LobbyGUI(client).setupLobbyPanel();
    }

    public void deal(int wager) {
        client.deal(wager);
    }

    public void sitOut() {
        client.sitOut();
    }

    public void hit() {
        client.hit();
    }

    public void doubleDown() {
        client.doubleDown();
    }

}
