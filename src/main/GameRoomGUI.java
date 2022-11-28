package main;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author Brandon Murgic
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
    private JTextArea textAreaPlayer0;
    private JPanel panelGameRoomButtons;
    private JButton btnSitOut;
    private JButton btnLeaveRoom;
    private JButton btnBet50;
    private JButton btnBet100;
    private JButton btnBet500;
    private JButton btnDouble;
    private JButton btnHit;
    private JButton btnDealStand;
    private JLabel lblPlayerFunds;

    private JTextField[] playerWagers = new JTextField[6];
    private JTextArea[] playerTextAreas = new JTextArea[6];
    private TitledBorder[] playerBorders = new TitledBorder[6];
    private JButton[] takeSeatButtons = new JButton[5];
    Font titleFont = new Font("Roboto", Font.BOLD | Font.ITALIC, 36);
    Font stdFont = new Font("Roboto", Font.PLAIN, 12);
    Font smlFont = new Font("Roboto", Font.PLAIN, 10);
    Font bldFont = new Font("Roboto", Font.BOLD, 12);

    // Client variable
    private Client client;
    private int wager;

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

        for (int i = 0; playerTextAreas.length > i; i++) {
            if (i == 0)
                playerBorders[i] = new TitledBorder(null, "Dealer", TitledBorder.CENTER, TitledBorder.TOP, bldFont);
            else
                playerBorders[i] = new TitledBorder(null, "Player " + i, TitledBorder.CENTER, TitledBorder.TOP, bldFont);

            playerTextAreas[i] = new JTextArea();
            playerTextAreas[i].setEnabled(false);
            playerTextAreas[i].setBorder(playerBorders[i]);
            playerTextAreas[i].setFont(smlFont);
        }

        for (int i = 0; playerWagers.length > i; i++) {
            playerWagers[i] = new JTextField();
            playerWagers[i].setEnabled(false);
            playerWagers[i].setFont(stdFont);
        }

        for (int i = 0; takeSeatButtons.length > i; i++) {
            int finalI = i;
            takeSeatButtons[i] = new JButton();
            takeSeatButtons[i].setText("Take Seat");
            takeSeatButtons[i].setFont(stdFont);
            takeSeatButtons[i].addActionListener(evt -> sit(finalI + 1));
        }

        panelGameRoomTitle = new JPanel();
        lblTitle = new JLabel();
        panelGameRoom = new JPanel();
        textAreaPlayer1 = playerTextAreas[1];
        player1Wager = playerWagers[1];
        lblPlayer1Wager = new JLabel();
        btnTakeSeat1 = takeSeatButtons[0];
        btnTakeSeat2 = takeSeatButtons[1];
        textAreaPlayer2 = playerTextAreas[2];
        player2Wager = playerWagers[2];
        lblPlayer2Wager = new JLabel();
        btnTakeSeat3 = takeSeatButtons[2];
        textAreaPlayer3 = playerTextAreas[3];
        player3Wager = playerWagers[3];
        lblPlayer3Wager = new JLabel();
        btnTakeSeat4 = takeSeatButtons[3];
        textAreaPlayer4 = playerTextAreas[4];
        player4Wager = playerWagers[4];
        lblPlayer4Wager = new JLabel();
        btnTakeSeat5 = takeSeatButtons[4];
        textAreaPlayer5 = playerTextAreas[5];
        player5Wager = playerWagers[5];
        lblPlayer5Wager = new JLabel();
        textAreaPlayer0 = playerTextAreas[0];
        panelGameRoomButtons = new JPanel();
        btnSitOut = new JButton();
        btnLeaveRoom = new JButton();
        btnBet50 = new JButton();
        btnBet100 = new JButton();
        btnBet500 = new JButton();
        btnDouble = new JButton();
        btnHit = new JButton();
        btnDealStand = new JButton();
        wager = 0;
        lblPlayerFunds = new JLabel();

        //======== this ========
        setBackground(new Color(0x003300));
        setForeground(new Color(0x003300));
        var contentPane = getContentPane();

        //======== panelGameRoomTitle ========
        {
            panelGameRoomTitle.setBackground(new Color(0x003300));
            panelGameRoomTitle.setLayout(new GridBagLayout());
            ((GridBagLayout) panelGameRoomTitle.getLayout()).rowHeights = new int[]{0, 0};
            ((GridBagLayout) panelGameRoomTitle.getLayout()).rowWeights = new double[]{0.0, 1.0E-4};

            //---- lblTitle ----
            lblTitle.setText("Game Room");
            lblTitle.setFont(titleFont);
            lblTitle.setForeground(Color.white);
            panelGameRoomTitle.add(lblTitle, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

            //---- lbllPlayerFunds ----
            try {
                lblPlayerFunds.setText(String.valueOf(client.getPlayer().getAccountBalance()));
            } catch (Exception e) {
                lblPlayerFunds.setText("0");
            }

            lblPlayerFunds.setFont(new Font("Roboto", Font.BOLD | Font.ITALIC, 36));
            lblPlayerFunds.setForeground(Color.white);
            panelGameRoomTitle.add(lblPlayerFunds, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
        }

        //======== panelGameRoom ========
        {
            panelGameRoom.setBackground(new Color(0x003300));
            panelGameRoom.setForeground(Color.black);

            //---- lblPlayer1Wager ----
            lblPlayer1Wager.setText("Wager");
            lblPlayer1Wager.setFont(stdFont);
            lblPlayer1Wager.setPreferredSize(new Dimension(40, 17));
            lblPlayer1Wager.setHorizontalAlignment(SwingConstants.CENTER);
            lblPlayer1Wager.setForeground(Color.white);

            //---- btnTakeSeat1 ----
            btnTakeSeat1.setText("Take Seat");
            btnTakeSeat1.setFont(new Font("Roboto", Font.PLAIN, 12));
            btnTakeSeat1.addActionListener(evt -> sit(1));

            //---- btnTakeSeat2 ----
            btnTakeSeat2.setText("Take Seat");
            btnTakeSeat2.setFont(new Font("Roboto", Font.PLAIN, 12));
            btnTakeSeat2.addActionListener(evt -> sit(2));

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
            btnTakeSeat3.addActionListener(evt -> sit(3));

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
            btnTakeSeat4.addActionListener(evt -> sit(4));

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
            btnTakeSeat5.addActionListener(evt -> sit(5));

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
                                            .addComponent(textAreaPlayer0, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
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
                                                    .addComponent(textAreaPlayer0)
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
            btnSitOut.setFont(stdFont);
            btnSitOut.setEnabled(false);
            btnSitOut.addActionListener(e -> sitOut());

            //---- btnLeaveRoom ----
            btnLeaveRoom.setText("Leave Room");
            btnLeaveRoom.setFont(stdFont);
            btnLeaveRoom.addActionListener(e -> leaveRoom());

            //---- btnBet50 ----
            btnBet50.setText("Bet 50");
            btnBet50.setFont(stdFont);
            btnBet50.addActionListener(e -> setWager(50));
            btnBet50.setEnabled(false);

            //---- btnBet100 ----
            btnBet100.setText("Bet 100");
            btnBet100.setFont(stdFont);
            btnBet100.addActionListener(e -> setWager(100));
            btnBet100.setEnabled(false);

            //---- btnBet500 ----
            btnBet500.setText("Bet 500");
            btnBet500.setFont(stdFont);
            btnBet500.addActionListener(e -> setWager(500));
            btnBet500.setEnabled(false);

            //---- btnDouble ----
            btnDouble.setText("Bet Double");
            btnDouble.setFont(stdFont);
            btnDouble.addActionListener(e -> setWager(2));
            btnDouble.setEnabled(false);

            //---- btnHit ----
            btnHit.setText("HIT");
            btnHit.setFont(stdFont);
            btnHit.setEnabled(false);
            btnHit.addActionListener(e -> hit());

            //---- btnDeal ----
            btnDealStand.setText("DEAL");
            btnDealStand.setFont(titleFont);
            btnDealStand.addActionListener(e -> deal());
            btnDealStand.setEnabled(false);

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
                                    .addComponent(btnDealStand, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
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
                                                    .addComponent(btnDealStand, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        setLocationRelativeTo(null);

    }

    public static void main(String args[]) throws Exception {
        new GameRoomGUI(new Client()).setupGameRoom();
    }

    public void setupGameRoom() {
        setVisible(true);
    }

    public void sit(int seatIndex) {
        refreshGUI();
        client.sit(seatIndex);
        btnBet50.setEnabled(true);
        btnBet100.setEnabled(true);
        btnBet500.setEnabled(true);
        btnDouble.setEnabled(true);
        btnHit.setEnabled(true);
        btnDealStand.setEnabled(true);
        btnLeaveRoom.setEnabled(false);
        btnSitOut.setEnabled(true);

        for (int i = 1; client.getRoom().getMAXPLAYERS() > i; i++) {
            if (seatIndex == i) {
                takeSeatButtons[i - 1].setText("Leave Seat");
                takeSeatButtons[i - 1].removeActionListener(takeSeatButtons[seatIndex - 1].getActionListeners()[0]);
                takeSeatButtons[i - 1].addActionListener(e -> leaveSeat());
                playerWagers[i].setEnabled(true);
            } else {
                takeSeatButtons[i - 1].setEnabled(false);
            }

        }

    }

    public void leaveSeat() {
        btnBet50.setEnabled(false);
        btnBet100.setEnabled(false);
        btnBet500.setEnabled(false);
        btnDouble.setEnabled(false);
        btnHit.setEnabled(false);
        btnDealStand.setEnabled(false);
        btnLeaveRoom.setEnabled(true);
        btnSitOut.setEnabled(false);

        for (int i = 1; client.getRoom().getMAXPLAYERS() > i; i++) {
            if ((client.getPlayer().getSeatIndex() - 1) != i)
                takeSeatButtons[i].setEnabled(true);
            else {
                takeSeatButtons[client.getPlayer().getSeatIndex() - 1].setText("Take Seat");
                takeSeatButtons[client.getPlayer().getSeatIndex() - 1].removeActionListener(takeSeatButtons[client.getPlayer().getSeatIndex() - 1].getActionListeners()[0]);
                takeSeatButtons[client.getPlayer().getSeatIndex() - 1].addActionListener(e -> sit(client.getPlayer().getSeatIndex()));
            }
        }

        //client.leaveSeat();
        refreshGUI();
    }

    public void sitOut() {
        refreshGUI();
        client.sitOut();
        btnBet50.setEnabled(false);
        btnBet100.setEnabled(false);
        btnBet500.setEnabled(false);
        btnDouble.setEnabled(false);
        btnHit.setEnabled(false);
        btnDealStand.setEnabled(false);
        btnLeaveRoom.setEnabled(false);
    }

    public void startRound() {
        wager = 0;
        btnDealStand.setText("DEAL");
        btnDealStand.removeActionListener(btnDealStand.getActionListeners()[0]);
        btnDealStand.addActionListener(e -> deal());
        btnBet50.setEnabled(true);
        btnBet100.setEnabled(true);
        btnBet500.setEnabled(true);
        btnDouble.setEnabled(true);
        btnHit.setEnabled(true);
        btnDealStand.setEnabled(true);
        playerWagers[client.getPlayer().getSeatIndex()].setEnabled(true);
    }

    public void endRound() {
        refreshGUI();

        btnBet50.setEnabled(false);
        btnBet100.setEnabled(false);
        btnBet500.setEnabled(false);
        btnDouble.setEnabled(false);
        btnHit.setEnabled(false);
        btnDealStand.setEnabled(false);
        playerWagers[client.getPlayer().getSeatIndex()].setEnabled(false);
    }

    public void setWager(int wager) {

        if (wager == 2) {
            this.wager *= 2;
            client.doubleDown();
        } else
            this.wager += wager;

        refreshGUI();

    }

    public void refreshGUI() {
    	try {
            java.awt.EventQueue.invokeLater(() -> {
                for (int i = 0; client.getRoom().getMAXPLAYERS() > i; i++) {
                    if (i == 0)  // Update the dealers hand
                        playerTextAreas[i].setText(String.valueOf(client.getRoom().getPlayersInRoom()[i].showHand() + "\n\n" + client.getRoom().getPlayersInRoom()[i].getScore()));
                    else {  // Check all the other seats
                        if (i == client.getPlayer().getSeatIndex()) {  // If the seat is the current player's seat
                            playerBorders[i].setTitle(client.getPlayer().getUsername());
                            playerTextAreas[i].setText(String.valueOf(client.getRoom().getPlayersInRoom()[i].showHand() + "\n\n" + client.getRoom().getPlayersInRoom()[i].getScore()));
                            playerWagers[i].setText(String.valueOf(wager));

                            // Check if player is bust or blackjack
                            if (client.getRoom().getPlayersInRoom()[i].getScore().equals("bust") || client.getRoom().getPlayersInRoom()[i].getScore().equals("blackjack")) {
                                endRound();
                            }

                        } else {  // If there is a player in that seat
                            if (client.getRoom().getPlayersInRoom()[i] != null) {
                                playerBorders[i].setTitle(client.getRoom().getPlayersInRoom()[i].getUsername());
                                playerTextAreas[i].setText(String.valueOf(client.getRoom().getPlayersInRoom()[i].showHand() + "\n\n" + client.getRoom().getPlayersInRoom()[i].getScore()));
                                playerWagers[i].setText(String.valueOf(client.getRoom().getPlayersInRoom()[i].getWager()));
                            } else {  // If there is no player in that seat
                                playerBorders[i].setTitle("Player " + i);
                                playerTextAreas[i].setText("");
                                playerWagers[i].setText("");
                            }
                        }
                    }
                }

                revalidate();
                repaint();
            });
        } catch (Exception ignored) {}

    }

    public void leaveRoom() {
        client.leaveRoom();
        setVisible(false);
        dispose();
        new LobbyGUI(client).setupLobbyPanel();
    }

    public void deal() {
        if (this.wager == 0)
            JOptionPane.showMessageDialog(panelGameRoom, "Set a wager!");
        else {
            client.deal(this.wager);

            btnDealStand.setText("STAND");
            btnDealStand.removeActionListener(btnDealStand.getActionListeners()[0]);
            btnDealStand.addActionListener(e -> stand());

            btnBet50.setEnabled(false);
            btnBet100.setEnabled(false);
            btnBet500.setEnabled(false);
        }
    }

    public void hit() {
        client.hit();
        refreshGUI();
    }

    public void stand() {
        client.stand();
        endRound();
    }

}