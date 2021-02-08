package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private final char[] LIST_OPERATORS= {'+', '-', '*', '/'};
    private final int INDENT_SIZE = 10;
    private final char SPACE_CHAR = ' ';

    private StringBuilder expression;
    private StringBuilder variable;

    private JPanel panelOutput;
    private JPanel panelNumber;
    private JPanel panelOperators;

    private JTextField textField;
    private JButton[] numbers;
    private JButton[] operators;
    private JButton dot;
    private JButton result;
    private JButton clean;
    private JButton back;

    Calculator(){
        setTitle("Калькулятор");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 150, 450, 300);
        setLayout(new BorderLayout());

        expression = new StringBuilder("");
        variable = new StringBuilder("");

        panelOutput = new JPanel();

        textField = new JTextField();
        textField.setEnabled(false);
        textField.setPreferredSize(new Dimension(400, 40));

        panelOutput.setBorder(BorderFactory.createEmptyBorder(INDENT_SIZE * 2, INDENT_SIZE * 2,
                                                              INDENT_SIZE, INDENT_SIZE * 2));
        panelOutput.add(textField);

        panelNumber = new JPanel();
        panelNumber.setLayout(new GridLayout(4, 3, INDENT_SIZE, INDENT_SIZE));
        panelNumber.setBorder(BorderFactory.createEmptyBorder(INDENT_SIZE, INDENT_SIZE, INDENT_SIZE, INDENT_SIZE));

        numbers = new JButton[10];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = new JButton(String.valueOf(i));
            numbers[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    variable.append(((JButton)e.getSource()).getText());
                    expression.append(((JButton)e.getSource()).getText());
                    textField.setText(String.valueOf(expression));
                }
            });
        }

        for (int i = 3; i < numbers.length; i += 3) {
            for (int j = 0; j < 3; j++) {
                panelNumber.add(numbers[numbers.length - i + j]);
            }
        }

        panelNumber.add(numbers[0]);

        dot = new JButton(".");
        dot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (variable.indexOf(((JButton)e.getSource()).getText()) == -1 && expression.length() != 0){
                    variable.append(((JButton)e.getSource()).getText());
                    expression.append(((JButton)e.getSource()).getText());
                    textField.setText(String.valueOf(expression));
                }
            }
        });
        panelNumber.add(dot);

        result = new JButton("=");
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expression.length() == 0) {
                    textField.setText("");
                    return;
                }

                String[] strValues = (String.valueOf(expression)).split(String.valueOf(SPACE_CHAR));

                expression.append(SPACE_CHAR + "=" + SPACE_CHAR);

                if (expression.indexOf(dot.getText()) == -1) {
                    long res = returnResultLong(strValues);
                    expression.append(res);
                } else {
                    double res = returnResultDouble(strValues);
                    expression.append(res);
                }

                textField.setText(String.valueOf(expression));

                variable = new StringBuilder();
                expression = new StringBuilder();
            }
        });

        panelNumber.add(result);


        panelOperators = new JPanel();
        panelOperators.setLayout(new GridLayout(4, 2, INDENT_SIZE, INDENT_SIZE));
        panelOperators.setBorder(BorderFactory.createEmptyBorder(INDENT_SIZE, INDENT_SIZE, INDENT_SIZE, INDENT_SIZE));

        back = new JButton("⟵");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int length = expression.length();
                if (length == 0) {
                    textField.setText("");
                    return;
                }
                if (expression.charAt(length - 1) == SPACE_CHAR) {
                    expression.delete(length - 3, length);
                } else {
                    if (variable.length() == 0) {
                        variable.append(expression);
                    }
                    variable.deleteCharAt(length - 1);
                    expression.deleteCharAt(length - 1);
                }
                textField.setText(String.valueOf(expression));
            }
        });

        panelOperators.add(back);

        clean = new JButton("Clean");
        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                variable = new StringBuilder();
                expression = new StringBuilder();
                textField.setText(String.valueOf(expression));
            }
        });

        panelOperators.add(clean);

        operators = new JButton[LIST_OPERATORS.length];

        for (int i = 0; i < operators.length; i++) {
            operators[i] = new JButton();
            operators[i].setText(String.valueOf(LIST_OPERATORS[i]));
            operators[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String operator = ((JButton)e.getSource()).getText();
                    if (expression.length() == 0) {
                        if (operator.equals("-")) {
                            variable.append(operator);
                            expression.append(operator);
                        }
                    } else {
                        if (variable.length() == 0) {
                            expression.delete(expression.length() - 3, expression.length());
                        } else {
                            variable = new StringBuilder();
                        }
                        expression.append(SPACE_CHAR + operator + SPACE_CHAR);
                    }
                    textField.setText(String.valueOf(expression));
                }
            });
            panelOperators.add(operators[i]);
        }

        add(panelOutput, BorderLayout.NORTH);
        add(panelNumber, BorderLayout.CENTER);
        add(panelOperators, BorderLayout.EAST);

        setVisible(true);
    }

    private Long returnResultLong(String[] arrayStr) {
        long res = Long.parseLong(arrayStr[0]);
        for (int i = 0; i < arrayStr.length; i++) {
            switch (arrayStr[i]){
                case "+":
                    res += Long.parseLong(arrayStr[i + 1]);
                    break;
                case "-":
                    res -= Long.parseLong(arrayStr[i + 1]);
                    break;
                case "*":
                    res *= Long.parseLong(arrayStr[i + 1]);
                    break;
                case "/":
                    res /= Long.parseLong(arrayStr[i + 1]);
                    break;
            }
        }
        return res;
    }

    private Double returnResultDouble(String[] arrayStr) {
        double res = Double.parseDouble(arrayStr[0]);
        for (int i = 0; i < arrayStr.length; i++) {
            switch (arrayStr[i]){
                case "+":
                    res += Double.parseDouble(arrayStr[i + 1]);
                    break;
                case "-":
                    res -= Double.parseDouble(arrayStr[i + 1]);
                    break;
                case "*":
                    res *= Double.parseDouble(arrayStr[i + 1]);
                    break;
                case "/":
                    res /= Double.parseDouble(arrayStr[i + 1]);
                    break;
            }
        }
        return res;
    }
}
