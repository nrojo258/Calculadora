package GUI_04;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

public class ventana4 extends JFrame{
    public ventana4() {
        setSize(500, 300);
        setLayout(null); 

        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0,0,500,300);

        JTextField cajaTexto = new JTextField();
        cajaTexto.setBounds(50, 30, 150, 30);
        cajaTexto.setText("Escribe tu E-Mail");
        cajaTexto.setBorder(new LineBorder(Color.GRAY,1));
        panel.add(cajaTexto);

        JTextField cajaTexto2 = new JTextField();
        cajaTexto2.setBounds(50, 100, 250, 50);
        cajaTexto2.setText("First Name please");
        cajaTexto2.setForeground(Color.BLUE);
        cajaTexto2.setFont(new Font("Arial", Font.PLAIN, 18)); 
        cajaTexto2.setBorder(new LineBorder(Color.GRAY, 1));
        panel.add(cajaTexto2);

        panel.setBounds(50, 50, 300, 200); 
        this.add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
