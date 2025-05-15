package GUI_01;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ventana extends JFrame {
    public ventana() {
        setSize(500, 500);
        setTitle("Button Example");
        setLayout(null); 

        JPanel panel = new JPanel();
        panel.setLayout(null); 

        JButton button = new JButton("Click");
        button.setBounds(200, 200, 100, 50); 
        panel.add(button);

        panel.setBounds(0, 0, 500, 500); 
        this.add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
