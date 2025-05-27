import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class botones {
    public JButton[] botonesNumeros = new JButton[10];
    public JButton botonSumar, botonRestar, botonMultiplicar, botonDividir;
    public JButton botonDecimal, botonIgual, botonLimpiar, botonBorrar, botonNegar;
    public JButton botonBorrarHistorial, botonMostrarHistorial;

    public JPanel crearPanelBotones(Font fuente, ActionListener listener) {
        Color fondoNumeros = new Color(80, 80, 80);
        Color fondoOperadores = new Color(0, 100, 255);
        Color fondoEspeciales = new Color(200, 50, 50);
        Color fondoDecimal = new Color(80, 80, 80);

        botonSumar = crearBoton("+", fondoOperadores, fuente, listener);
        botonRestar = crearBoton("-", fondoOperadores, fuente, listener);
        botonMultiplicar = crearBoton("*", fondoOperadores, fuente, listener);
        botonDividir = crearBoton("/", fondoOperadores, fuente, listener);
        botonIgual = crearBoton("=", fondoOperadores, fuente, listener);

        botonLimpiar = crearBoton("CE", fondoEspeciales, fuente, listener);
        botonBorrar = crearBoton("Del", fondoEspeciales, fuente, listener);
        botonNegar = crearBoton("+/-", fondoEspeciales, fuente, listener);
        botonDecimal = crearBoton(".", fondoDecimal, fuente, listener);

        for (int i = 0; i < 10; i++) {
            botonesNumeros[i] = crearBoton(String.valueOf(i), fondoNumeros, fuente, listener);
        }

        JPanel panel = new JPanel(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(230, 230, 230));

        panel.add(botonNegar);
        panel.add(botonLimpiar);
        panel.add(botonBorrar);
        panel.add(botonDividir);

        panel.add(botonesNumeros[7]);
        panel.add(botonesNumeros[8]);
        panel.add(botonesNumeros[9]);
        panel.add(botonMultiplicar);

        panel.add(botonesNumeros[4]);
        panel.add(botonesNumeros[5]);
        panel.add(botonesNumeros[6]);
        panel.add(botonRestar);

        panel.add(botonesNumeros[1]);
        panel.add(botonesNumeros[2]);
        panel.add(botonesNumeros[3]);
        panel.add(botonSumar);

        panel.add(botonDecimal);
        panel.add(botonesNumeros[0]);
        panel.add(botonIgual);
        panel.add(new JLabel());

        return panel;
    }

    public void crearBotonesHistorial(Font fuente, ActionListener listener) {
        botonMostrarHistorial = new JButton("Mostrar Historial");
        botonMostrarHistorial.setFont(fuente.deriveFont(24f));
        botonMostrarHistorial.setBackground(new Color(0, 102, 204));
        botonMostrarHistorial.setForeground(Color.WHITE);
        botonMostrarHistorial.addActionListener(listener);

        botonBorrarHistorial = new JButton("Limpiar Historial");
        botonBorrarHistorial.setFont(fuente.deriveFont(24f));
        botonBorrarHistorial.setBackground(new Color(0, 102, 204));
        botonBorrarHistorial.setForeground(Color.WHITE);
        botonBorrarHistorial.addActionListener(listener);
    }

    private JButton crearBoton(String texto, Color color, Font fuente, ActionListener listener) {
        JButton boton = new JButton(texto);
        boton.setFont(fuente.deriveFont(30f));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusable(false);
        boton.addActionListener(listener);
        return boton;
    }
}
