package Client_Side;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.*;
public class Client extends JFrame implements ActionListener {

    JPanel P1;
    JTextField t1;
    JButton b1;
    static JTextArea A1;
    static Socket sock;
    static DataOutputStream dout;
    static DataInputStream din;

    public Client() {

        P1 = new JPanel();
        P1.setLayout(null);
        P1.setBackground(new Color(243, 179, 64));
        P1.setBounds(0, 0, 470, 70);
        add(P1);



        /*setting back option image*/

        ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("Server_Side/Icon/back.png"));
        Image i2 = I1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon I3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(I3);
        l1.setBounds(410, 10, 50, 50);
        P1.add(l1);

        // perform events of back option
        l1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

        /*setting image option image*/

        ImageIcon I4 = new ImageIcon(ClassLoader.getSystemResource("Server_Side/Icon/Reyna.jpg"));
        Image i3 = I4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon I5 = new ImageIcon(i3);
        JLabel l2 = new JLabel(I5);
        l2.setBounds(5, 10, 50, 50);
        P1.add(l2);

        /* adding names*/

        JLabel l3 = new JLabel("Bulbasaur");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        l3.setForeground(Color.WHITE);
        l3.setBounds(68, 18, 100, 20);
        P1.add(l3);

        /* adding active status*/

        JLabel l4 = new JLabel("Active now");
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 10));
        l4.setForeground(Color.WHITE);
        l4.setBounds(70, 37, 100, 20);
        P1.add(l4);

        //qdding text field

        t1 = new JTextField();
        t1.setBounds(1, 559, 390, 40);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t1);

        //adding send button

        b1 = new JButton("Send");
        b1.setBounds(396, 559, 70, 40);
        b1.setBackground(new Color(243, 179, 64));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        b1.addActionListener(this);
        add(b1);

        // adding text area to display messages

        A1 = new JTextArea();
        A1.setBounds(5, 75, 460, 480);
        A1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        A1.setLineWrap(true);
        A1.setWrapStyleWord(true);
        A1.setEditable(false);
        add(A1);

        /*setting chat box properties*/

        getContentPane().setBackground(new Color(244, 243, 239));
        setLayout(null);
        setSize(470, 600);
        setLocation(950, 160);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        try {
            String out = t1.getText();
            A1.setText(A1.getText() + "\n"+"you: " + out);
            dout.writeUTF(out);
            t1.setText("");
        } catch (Exception exc) {

        }

    }

    public static void main(String args[]) {

        new Client().setVisible(true);

        String msginput = "";
        try {
            sock = new Socket("127.0.0.1", 8001);
            din = new DataInputStream(sock.getInputStream());
            dout = new DataOutputStream(sock.getOutputStream());



            while (true){
                msginput= din.readUTF();
                A1.setText(A1.getText()+"\n"+msginput);
            }

        } catch (Exception e) {

        }
    }
}