package GUI_03;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ventana3 extends JFrame {
    public ventana3() {
        setSize(500, 500);
        setLayout(null); 

        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, 500, 500); 

        JLabel texto = new JLabel("Lenguajes:");
        texto.setBounds(50, 50, 300, 25); 
        panel.add(texto);

        JRadioButton radioBoton1 = new JRadioButton("Java", false);
        radioBoton1.setBounds(50, 75, 100, 25);
        panel.add(radioBoton1);

        
        JRadioButton radioBoton2 = new JRadioButton("Php", false);
        radioBoton2.setBounds(50, 100, 100, 25);
        panel.add(radioBoton2);

        
        JRadioButton radioBoton3 = new JRadioButton("C++", false);
        radioBoton3.setBounds(50, 125, 100, 25);
        panel.add(radioBoton3);

        JButton button1 = new JButton("Validar");
        button1.setBounds(50, 150, 100, 25); 
        panel.add(button1);

        add(panel); 

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
