package GUI_02;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class ventana2 extends JFrame {
    public ventana2() {
        setSize(500, 500);
        setTitle("El mejor titulo");
        setLayout(null); 

        JPanel panel = new JPanel();
        panel.setLayout(null); 

        JButton button1 = new JButton("Click");
        button1.setBounds(150, 150, 100, 40); 
        button1.setForeground(Color.BLUE);
        button1.setFont(new Font ("cooper black",3,20));
        panel.add(button1);

        JButton button2 = new JButton();
        button2.setBounds(150, 220, 100, 40); 
        ImageIcon clicAqui = new ImageIcon("C:\\Users\\NERQI\\Documents\\Calculadora\\src\\GUI_02\\boton.png");
        button2.setIcon(new ImageIcon(clicAqui.getImage().getScaledInstance(button2.getWidth(),button2.getHeight(), Image.SCALE_SMOOTH)));
        button2.setForeground(Color.BLUE);
        panel.add(button2);

        panel.setBounds(0, 0, 500, 500); 
        this.add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}