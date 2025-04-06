//package scientificlculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScienficCalc extends JFrame {

    private double num1, num2, result;
    private String opr;
    private JTextField jTextField1;
    private JRadioButton jRadioButton1, jRadioButton2;
    private JButton[] buttons;






    public ScienficCalc() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Scientific Calculator");
        setResizable(false);

        jTextField1 = new JTextField();
        jTextField1.setFont(new Font("Tahoma", Font.BOLD, 18));
        jTextField1.setHorizontalAlignment(JTextField.RIGHT);
        jTextField1.setEditable(false);

        jRadioButton1 = new JRadioButton("ON");
        jRadioButton2 = new JRadioButton("OFF");
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);

        jRadioButton1.addActionListener(e -> enable());
        jRadioButton2.addActionListener(e -> disable());

        String[] buttonLabels = {
                "sqrt", "exp", "cos", "sin", "tan",
                "log", "sinh", "cosh", "tanh", "1/x",
                "%", "B", "clr", "+", "x^y",
                "7", "8", "9", "-", "x^3",
                "4", "5", "6", "*", "x^2",
                "1", "2", "3", "/", "n!",
                "0", ".", "=", "+/-"
        };

        buttons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Tahoma", Font.BOLD, 14));
            buttons[i].addActionListener(new ButtonClickListener());
        }

        JPanel panel = new JPanel(new GridLayout(0, 5, 5, 5));
        panel.add(jRadioButton1);
        panel.add(jRadioButton2);
        panel.add(new JLabel()); // Empty space
        panel.add(new JLabel()); // Empty space
        panel.add(new JLabel()); // Empty space

        for (JButton button : buttons) {
            panel.add(button);
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jTextField1, BorderLayout.NORTH);
        getContentPane().add(panel, BorderLayout.CENTER);

        pack();
    }

    public void enable() {
        jTextField1.setEnabled(true);
        jRadioButton1.setEnabled(false);
        jRadioButton2.setEnabled(true);
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
    }

    @Override
    public void disable() {
        jTextField1.setEnabled(false);
        jRadioButton1.setEnabled(true);
        jRadioButton2.setEnabled(false);
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = ((JButton) e.getSource()).getText();

            switch (command) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case ".":
                    jTextField1.setText(jTextField1.getText() + command);
                    break;
                case "clr":
                    jTextField1.setText("");
                    break;
                case "B":
                    String text = jTextField1.getText();
                    if (text.length() > 0) {
                        jTextField1.setText(text.substring(0, text.length() - 1));
                    }
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                case "x^y":
                    num1 = Double.parseDouble(jTextField1.getText());
                    opr = command;
                    jTextField1.setText("");
                    break;
                case "=":
                    num2 = Double.parseDouble(jTextField1.getText());
                    calculateResult();
                    break;
                case "sqrt":
                    double sqrtVal = Math.sqrt(Double.parseDouble(jTextField1.getText()));
                    jTextField1.setText(String.valueOf(sqrtVal));
                    break;
                case "exp":
                    double expVal = Math.exp(Double.parseDouble(jTextField1.getText()));
                    jTextField1.setText(String.valueOf(expVal));
                    break;
                case "cos":
                    double cosVal = Math.cos(Double.parseDouble(jTextField1.getText()));
                    jTextField1.setText(String.valueOf(cosVal));
                    break;
                case "sin":
                    double sinVal = Math.sin(Double.parseDouble(jTextField1.getText()));
                    jTextField1.setText(String.valueOf(sinVal));
                    break;
                case "tan":
                    double tanVal = Math.tan(Double.parseDouble(jTextField1.getText()));
                    jTextField1.setText(String.valueOf(tanVal));
                    break;
                case "log":
                    double logVal = Math.log(Double.parseDouble(jTextField1.getText()));
                    jTextField1.setText(String.valueOf(logVal));
                    break;
                case "sinh":
                    double sinhVal = Math.sinh(Double.parseDouble(jTextField1.getText()));
                    jTextField1.setText(String.valueOf(sinhVal));
                    break;
                case "cosh":
                    double coshVal = Math.cosh(Double.parseDouble(jTextField1.getText()));
                    jTextField1.setText(String.valueOf(coshVal));
                    break;
                case "tanh":
                    double tanhVal = Math.tanh(Double.parseDouble(jTextField1.getText()));
                    jTextField1.setText(String.valueOf(tanhVal));
                    break;
                case "1/x":
                    double reciprocal = 1 / Double.parseDouble(jTextField1.getText());
                    jTextField1.setText(String.valueOf(reciprocal));
                    break;
                case "x^2":
                    double square = Math.pow(Double.parseDouble(jTextField1.getText()), 2);
                    jTextField1.setText(String.valueOf(square));
                    break;
                case "x^3":
                    double cube = Math.pow(Double.parseDouble(jTextField1.getText()), 3);
                    jTextField1.setText(String.valueOf(cube));
                    break;
                case "n!":
                    int n = Integer.parseInt(jTextField1.getText());
                    if (n < 0) {
                        jTextField1.setText("Error");
                    } else {
                        long factorial = 1;
                        for (int i = 1; i <= n; i++) {
                            factorial *= i;
                        }
                        jTextField1.setText(String.valueOf(factorial));
                    }
                    break;
                case "+/-":
                    double negate = Double.parseDouble(jTextField1.getText()) * -1;
                    jTextField1.setText(String.valueOf(negate));
                    break;
            }
        }
    }

    private void calculateResult() {
        switch (opr) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    jTextField1.setText("Error");
                    return;
                }
                break;
            case "%":
                result = num1 % num2;
                break;
            case "x^y":
                result = Math.pow(num1, num2);
                break;
        }
        jTextField1.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new ScienficCalc().setVisible(true);
        });
    }
}



