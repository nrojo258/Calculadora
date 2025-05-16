package GUI_05;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventana5 extends JFrame{
    public ventana5() {
        setSize(500, 400);
        setTitle("ComboBox Example");
        setLayout(null); 

        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0,0,400,250);

        JLabel texto = new JLabel("Programming language Selected: ");
        texto.setBounds(60, 20, 250, 25); 
        panel.add(texto);

        String[] language = {"C","C++","C#","Java","PHP"};
        JComboBox<String> listaDesplegable1 = new JComboBox<>(language);
        listaDesplegable1.setBounds(30,80,100,25);
        panel.add(listaDesplegable1);

        JButton boton1 = new JButton("Show");
        boton1.setBounds(200, 80, 80, 25);

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLanguage = (String) listaDesplegable1.getSelectedItem();
                texto.setText("Programming language Selected: " + selectedLanguage);
            }
        });
        panel.add(boton1);

        panel.setBounds(50, 50, 300, 200); 
        this.add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
