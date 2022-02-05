import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.EmptyStackException;

public class Calculator extends JFrame implements ActionListener, KeyListener {

    private static JTextField answerScreen;
    private static Calculator calculator_instance = null;
    private final Font answerScreenFont = new Font("SansSerif", Font.BOLD, 50);
    private final Font buttonFont = new Font("SansSerif", Font.BOLD, 30);

    private Calculator() {
    }

    public static Calculator getInstance() {
        if (calculator_instance == null)
            calculator_instance = new Calculator();
        return calculator_instance;
    }

    public void CreateAndShowCalculator() {
        JFrame mainFrame;
        mainFrame = new JFrame("Calculator");
        mainFrame.setMinimumSize(new Dimension(250, 500));

        try {
            mainFrame.setIconImage(ImageIO.read(new File("src/main/resources/calculator.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addComponents(mainFrame.getContentPane());
        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);


    }

    public void addComponents(Container pane) {
        JPanel mainPanel, buttonPanel;
        final String[][] buttonText = new String[][]{{"7", "8", "9", "+"}, {"4", "5", "6", "-"}, {"3", "2", "1", "*"}, {"(", "0", ")", "/"}, {"clr", "^", "%", "="}};

        mainPanel = new JPanel(new BorderLayout());

        answerScreen = new JTextField(10);
        answerScreen.setFont(answerScreenFont);
        answerScreen.setBackground(new Color(112, 169, 49));
        answerScreen.addKeyListener(this);
        answerScreen.requestFocus();

        buttonPanel = new JPanel(new GridLayout(buttonText.length, buttonText[0].length));
        for (int i = 0; i < buttonText.length; i++) {
            for (int j = 0; j < buttonText[i].length; j++) {
                JButton btn = new JButton(buttonText[i][j]);
                btn.setFont(buttonFont);
                btn.setBackground(new Color(130, 130, 130));

                btn.addActionListener(this);
                buttonPanel.add(btn);
            }
        }
        mainPanel.add(answerScreen, BorderLayout.PAGE_START);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        pane.add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (((JButton) e.getSource()).getText()) {
            case "1":
                answerScreen.setText(answerScreen.getText().concat("1"));
                break;
            case "2":
                answerScreen.setText(answerScreen.getText().concat("2"));
                break;
            case "3":
                answerScreen.setText(answerScreen.getText().concat("3"));
                break;
            case "4":
                answerScreen.setText(answerScreen.getText().concat("4"));
                break;
            case "5":
                answerScreen.setText(answerScreen.getText().concat("5"));
                break;
            case "6":
                answerScreen.setText(answerScreen.getText().concat("6"));
                break;
            case "7":
                answerScreen.setText(answerScreen.getText().concat("7"));
                break;
            case "8":
                answerScreen.setText(answerScreen.getText().concat("8"));
                break;
            case "9":
                answerScreen.setText(answerScreen.getText().concat("9"));
                break;
            case "0":
                answerScreen.setText(answerScreen.getText().concat("0"));
                break;
            case "(":
                answerScreen.setText(answerScreen.getText().concat("("));
                break;
            case ")":
                answerScreen.setText(answerScreen.getText().concat(")"));
                break;
            case "*":
                answerScreen.setText(answerScreen.getText().concat("*"));
                break;
            case "+":
                answerScreen.setText(answerScreen.getText().concat("+"));
                break;
            case "-":
                answerScreen.setText(answerScreen.getText().concat("-"));
                break;
            case "/":
                answerScreen.setText(answerScreen.getText().concat("/"));
                break;
            case "^":
                answerScreen.setText(answerScreen.getText().concat("^"));
                break;
            case "%":
                answerScreen.setText(answerScreen.getText().concat("%"));
                break;
            case "clr":
                answerScreen.setText("");
                break;
            case "=":
                Calculate(answerScreen.getText());

        }
    }

    public static void Calculate(String expressionText) {
        Expression expression = new ExpressionBuilder(expressionText).build();
        double answer = 0;
        try {
            answer = expression.evaluate();
            if (answer % 1 == 0)
                answerScreen.setText(Integer.toString((int) answer));
            else
                answerScreen.setText(Double.toString(answer));
        } catch (EmptyStackException | UnknownFunctionOrVariableException e) {
            answerScreen.setText("ERROR");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        System.out.println(keyChar);
        if (keyChar == '\n')
            Calculate(answerScreen.getText());
        else if (keyChar == '\u001B') {
            answerScreen.setText("");
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    public String getScreenText(){
        return answerScreen.getText();
    }
}
