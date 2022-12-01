//import javafx.scene.control.Labeled;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
public class GUI {
    JFrame theFrame;
    public static JLabel label4 ,label5;
    static void setOrginal(String orginal){
        label4.setText(orginal);
    }
    public static void setCompression(String compression){
        label5.setText(compression);
    }
    public GUI() {
        theFrame = new JFrame("Huffman");
        JButton b = new JButton("Execute");
        b.setBackground(Color.gray);
        b.setBounds(80, 150, 80, 40);


        JTextField text1, text2, text3;
        text1 = new JTextField();
        text1.setBounds(20, 50, 150, 20);
        text2 = new JTextField();
        text2.setBounds(20, 80, 150, 20);
        text3 = new JTextField();
        text3.setBounds(20, 110, 150, 20);

        theFrame.add(text1);
        theFrame.add(text2);
        theFrame.add(text3);


        JLabel label1, label2, label3 ,label6,label7 ;
        label1 = new JLabel("Enter the Symbols :");
        label1.setBounds(200, 50, 100, 30);
        label2 = new JLabel("Enter the Write File of Encode :");
        label2.setBounds(200, 80, 100, 30);
        label3 = new JLabel("Enter the Write File of Decode :");
        label3.setBounds(200, 110, 100, 30);


        JPanel panel = new JPanel();
        panel.setBounds(20, 20, 650, 550);
        panel.setBackground(Color.orange);
        panel.setLayout(new GridLayout(7, 1, 50, 25));
        panel.setBorder(new EmptyBorder(50, 150, 50, 150));

        panel.add(label1);
        panel.add(text1);
        panel.add(label2);
        panel.add(text2);
        panel.add(label3);
        panel.add(text3);
        panel.add(b);

        JPanel panel2 = new JPanel();
        panel2.setBounds(20, 600, 650, 200);
        panel2.setBackground(Color.gray);
        panel2.setLayout(new GridLayout(4, 1, 20, 20));
        panel2.setBorder(new EmptyBorder(20, 150, 20, 150));


        label4 = new JLabel("---");
        label4.setFont(new Font("Arial", Font.PLAIN, 25));
        label5 = new JLabel("---");
        label5.setFont(new Font("Arial", Font.PLAIN, 25));
        label6 = new JLabel("The Original Size = ");
        label6.setFont(new Font("Arial", Font.PLAIN, 25));
        label7 = new JLabel("The Compression Size = ");
        label7.setFont(new Font("Arial", Font.PLAIN, 25));


        panel2.add(label6);
        panel2.add(label4);
        panel2.add(label7);
        panel2.add(label5);


        theFrame.add(panel);
        theFrame.add(panel2);
        theFrame.setSize(700, 850);
        theFrame.setLayout(null);
        theFrame.setVisible(true);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String symbols = text1.getText();
                String writeFileEncode = text2.getText();
                String writeFileDecode = text3.getText();
                HuffmanMethods huffman=new HuffmanMethods();
                huffman.code(symbols,writeFileEncode);
                huffman.decode(writeFileEncode,writeFileDecode);
            }
        });
    }
}
