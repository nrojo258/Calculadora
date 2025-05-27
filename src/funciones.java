import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class funciones extends JFrame implements ActionListener, KeyListener {
    JTextField campoTexto;
    DefaultListModel<String> modeloHistorial;
    JList<String> listaHistorial;
    JScrollPane panelScrollHistorial;
    Font fuenteDigital;
    botones botones;

    boolean nuevaOperacion = false;
    boolean historialVisible = false;
    String operacionActual = "";

    double numero1 = 0, numero2 = 0, resultado = 0;
    char operador = ' ';
    double ultimoNumero2 = 0;
    char ultimoOperador = ' ';

    public funciones() {
        try {
            fuenteDigital = Font.createFont(Font.TRUETYPE_FONT, new File("src/fuentes/digital-7.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuenteDigital);
        } catch (Exception e) {
            fuenteDigital = new Font("Monospaced", Font.BOLD, 30);
        }

        setTitle("Calculadora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 620);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 230, 230));

        campoTexto = new JTextField();
        campoTexto.setBounds(20, 20, 340, 50);
        campoTexto.setFont(fuenteDigital.deriveFont(42f));
        campoTexto.setEditable(false);
        campoTexto.setBackground(new Color(240, 240, 240));
        campoTexto.setForeground(new Color(50, 50, 50));
        campoTexto.setHorizontalAlignment(SwingConstants.RIGHT);
        campoTexto.setMargin(new Insets(5, 5, 5, 10));
        add(campoTexto);

        modeloHistorial = new DefaultListModel<>();
        listaHistorial = new JList<>(modeloHistorial);
        listaHistorial.setFont(fuenteDigital.deriveFont(24f));
        listaHistorial.setBackground(new Color(240, 240, 240));
        listaHistorial.setForeground(new Color(50, 50, 50));
        listaHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panelScrollHistorial = new JScrollPane(listaHistorial);
        panelScrollHistorial.setBounds(380, 20, 200, 455);
        panelScrollHistorial.setVisible(false);
        add(panelScrollHistorial);

        botones = new botones();
        botones.crearBotonesHistorial(fuenteDigital, this);

        botones.botonMostrarHistorial.setBounds(20, 80, 340, 40);
        add(botones.botonMostrarHistorial);

        botones.botonBorrarHistorial.setBounds(380, 490, 200, 50);
        botones.botonBorrarHistorial.setVisible(false);
        add(botones.botonBorrarHistorial);

        JPanel panelBotones = botones.crearPanelBotones(fuenteDigital, this);
        panelBotones.setBounds(20, 140, 340, 400);
        add(panelBotones);

        listaHistorial.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = listaHistorial.locationToIndex(evt.getPoint());
                    if (index >= 0) {
                        String operacion = modeloHistorial.getElementAt(index);
                        if (operacion.contains("=")) {
                            String parteOperacion = operacion.substring(0, operacion.indexOf("=")).trim();
                            campoTexto.setText(parteOperacion);
                            operacionActual = parteOperacion;
                            nuevaOperacion = false;
                        }
                    }
                }
            }
        });

        campoTexto.addKeyListener(this);
        campoTexto.setFocusable(true);
        campoTexto.requestFocusInWindow();

    }

    private void alternarHistorial() {
        historialVisible = !historialVisible;
        panelScrollHistorial.setVisible(historialVisible);
        botones.botonBorrarHistorial.setVisible(historialVisible);
        botones.botonMostrarHistorial.setText(historialVisible ? "Ocultar Historial" : "Mostrar Historial");
        setSize(historialVisible ? 600 : 400, 620);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == botones.botonMostrarHistorial) {
            alternarHistorial();
        } else if (src == botones.botonBorrarHistorial) {
            modeloHistorial.clear();
        } else if (src == botones.botonLimpiar) {
            campoTexto.setText("");
            operacionActual = "";
        } else if (src == botones.botonBorrar) {
            if (!operacionActual.isEmpty()) {
                operacionActual = operacionActual.substring(0, operacionActual.length() - 1);
                campoTexto.setText(operacionActual);
            }
        } else if (src == botones.botonNegar) {
            try {
                if (!operacionActual.isEmpty()) {
                    double temp = Double.parseDouble(operacionActual.trim());
                    operacionActual = String.valueOf(temp * -1);
                    campoTexto.setText(operacionActual);
                }
            } catch (Exception ex) {
                campoTexto.setText("Error");
            }
        } else if (src == botones.botonDecimal) {
            if (nuevaOperacion) {
                operacionActual = "";
                nuevaOperacion = false;
            }
            String[] partes = operacionActual.split(" ");
            String ultimo = partes[partes.length - 1];
            if (!ultimo.contains(".")) {
                operacionActual += ".";
                campoTexto.setText(operacionActual);
            }
        } else if (src == botones.botonIgual) {
            calcularResultado();
        } else {
            for (int i = 0; i < 10; i++) {
                if (src == botones.botonesNumeros[i]) {
                    if (nuevaOperacion) {
                        operacionActual = "";
                        nuevaOperacion = false;
                    }
                    operacionActual += i;
                    campoTexto.setText(operacionActual);
                    return;
                }
            }

            if (src == botones.botonSumar || src == botones.botonRestar ||
                src == botones.botonMultiplicar || src == botones.botonDividir) {
                if (!operacionActual.endsWith(" ")) {
                    operacionActual += " " + ((JButton) src).getText() + " ";
                    campoTexto.setText(operacionActual);
                    operador = ((JButton) src).getText().charAt(0);
                    numero1 = Double.parseDouble(operacionActual.trim().split(" ")[0]);
                    nuevaOperacion = false;
                }
            }
        }
    }

    private void calcularResultado() {
        try {
            if (!nuevaOperacion) {
                String[] partes = operacionActual.trim().split(" ");
                if (partes.length < 3) return;
                numero1 = Double.parseDouble(partes[0]);
                operador = partes[1].charAt(0);
                numero2 = Double.parseDouble(partes[2]);
                ultimoNumero2 = numero2;
                ultimoOperador = operador;
            } else {
                numero1 = Double.parseDouble(campoTexto.getText());
                numero2 = ultimoNumero2;
                operador = ultimoOperador;
            }

            switch (operador) {
                case '+': resultado = numero1 + numero2; break;
                case '-': resultado = numero1 - numero2; break;
                case '*': resultado = numero1 * numero2; break;
                case '/':
                    if (numero2 == 0) {
                        campoTexto.setText("Error");
                        return;
                    } else {
                        resultado = numero1 / numero2;
                    }
                    break;
                default:
                    campoTexto.setText("Error");
                    return;
            }

            String operacion = numero1 + " " + operador + " " + numero2 + " = " + resultado;
            modeloHistorial.addElement(operacion);
            campoTexto.setText(String.valueOf(resultado));
            operacionActual = String.valueOf(resultado);
            nuevaOperacion = true;

        } catch (NumberFormatException ex) {
            campoTexto.setText("Error");
        }
    }

    // Soporte de teclado
    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();

        if (Character.isDigit(key)) {
            if (nuevaOperacion) {
                operacionActual = "";
                nuevaOperacion = false;
            }
            operacionActual += key;
            campoTexto.setText(operacionActual);
        }

        if (key == '.') {
            if (nuevaOperacion) {
                operacionActual = "";
                nuevaOperacion = false;
            }
            String[] partes = operacionActual.split(" ");
            String ultimo = partes[partes.length - 1];
            if (!ultimo.contains(".")) {
                operacionActual += '.';
                campoTexto.setText(operacionActual);
            }
        }

        if (key == '+' || key == '-' || key == '*' || key == '/') {
            if (!operacionActual.endsWith(" ")) {
                operacionActual += " " + key + " ";
                campoTexto.setText(operacionActual);
            }
        }

        if (key == '\n' || key == '=') {
            calcularResultado();
        }

        if (key == '\b') {
            if (!operacionActual.isEmpty()) {
                operacionActual = operacionActual.substring(0, operacionActual.length() - 1);
                campoTexto.setText(operacionActual);
            }
        }
    }

    @Override public void keyPressed(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
