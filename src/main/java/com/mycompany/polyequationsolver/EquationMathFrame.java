
package com.mycompany.polyequationsolver;
import javax.swing.*;
import java.awt.*;

public class EquationMathFrame extends JFrame {

    private JTextField eqField1, eqField2, xValueField;
    private JComboBox<String> operationBox;
    private JRadioButton infixRadio, postfixRadio, prefixRadio;
    private JButton solveBtn, evaluateBtn,clearBtn;
    private JTextArea resultArea;

    public EquationMathFrame() {
        setTitle("Math Equation Solver");
        setSize(517, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // color of font in my frame
        Color bg = new Color(255, 228, 232); //lightPink
        Color green = new Color(0, 180, 0); 
        Color pinkDark = new Color(231, 84, 128);
        Font fontBig = new Font("SansSerif", Font.BOLD, 14);
        Font fontNormal = new Font("SansSerif", Font.PLAIN, 13);

        getContentPane().setBackground(bg);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1,5,5));
        mainPanel.setBackground(bg);

        // equation1
        JPanel eq1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        eq1Panel.setBackground(bg);
        JLabel l1 = new JLabel("First Equation:    ");
        l1.setFont(fontBig); l1.setForeground(pinkDark);
        eqField1 = new JTextField(30); eqField1.setFont(fontNormal);
        eq1Panel.add(l1); eq1Panel.add(eqField1);
        mainPanel.add(eq1Panel);

        // equation2
        JPanel eq2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        eq2Panel.setBackground(bg);
        JLabel l2 = new JLabel("Second Equation:");
        l2.setFont(fontBig); l2.setForeground(pinkDark);
        eqField2 = new JTextField(30); eqField2.setFont(fontNormal);
        eq2Panel.add(l2); eq2Panel.add(eqField2);
        mainPanel.add(eq2Panel);

        // operation
        JPanel opPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        opPanel.setBackground(bg);
        JLabel opLabel = new JLabel("Choose Operation:");
        opLabel.setFont(fontBig); opLabel.setForeground(pinkDark);
        String[] ops = { "+", "−", "×", "÷" };
        operationBox = new JComboBox<>(ops);
        operationBox.setBackground(green); operationBox.setForeground(Color.WHITE);
        operationBox.setFont(fontNormal);
        opPanel.add(opLabel); opPanel.add(operationBox);
        mainPanel.add(opPanel);
       
        // displays
        JPanel formatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formatPanel.setBackground(bg);
        JLabel formatLabel = new JLabel("Display as:");
        formatLabel.setFont(fontBig); formatLabel.setForeground(pinkDark);
        infixRadio = new JRadioButton("Infix");
        postfixRadio = new JRadioButton("Postfix");
        prefixRadio = new JRadioButton("Prefix");
        ButtonGroup group = new ButtonGroup();
        group.add(infixRadio); group.add(postfixRadio); group.add(prefixRadio);
        infixRadio.setSelected(true);
        //same designe for group radio button so used for each loop
        for (JRadioButton r : new JRadioButton[]{infixRadio, postfixRadio, prefixRadio}) {
            r.setFont(fontNormal);
            r.setBackground(bg);
            r.setForeground(green);
            formatPanel.add(r);}
        
        formatPanel.add(formatLabel);
        mainPanel.add(formatPanel);

        clearBtn = new JButton("Clear");
        clearBtn.setBackground(Color.LIGHT_GRAY);clearBtn.setForeground(Color.BLACK);
        clearBtn.setFont(fontNormal);

        // Substitution
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionPanel.setBackground(bg);
        JLabel xLabel = new JLabel("Enter X value:");
        xLabel.setFont(fontBig); xLabel.setForeground(pinkDark);
        xValueField = new JTextField(7); xValueField.setFont(fontNormal);
        evaluateBtn = new JButton("Evaluate at X");
        evaluateBtn.setBackground(green); evaluateBtn.setForeground(Color.WHITE);
        evaluateBtn.setFont(fontNormal);
        solveBtn = new JButton("Solve Equation");
        solveBtn.setBackground(green); solveBtn.setForeground(Color.WHITE);
        solveBtn.setFont(fontNormal);
        actionPanel.add(xLabel);
        actionPanel.add(xValueField);
        actionPanel.add(evaluateBtn);
        actionPanel.add(solveBtn);
        actionPanel.add(clearBtn);
        mainPanel.add(actionPanel);

        // result
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBackground(bg);
        resultPanel.setBorder(BorderFactory.createTitledBorder("Answer"));
        resultArea = new JTextArea(8, 45);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);
        resultArea.setFont(fontNormal);
        JScrollPane scrollPane = new JScrollPane(resultArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
        
        

        setVisible(true);
        
        solveBtn.addActionListener(e -> {
    try {
        // read two equation as string
        String input1 = eqField1.getText();
        String input2 = eqField2.getText();

        //conveet from string to polylinked
        PolynomialLinkedList poly1 = EquParser.parse(input1);
        PolynomialLinkedList poly2 = EquParser.parse(input2);

        // choose operaion
        String operation = (String) operationBox.getSelectedItem();
        PolynomialLinkedList result = null;

        if (operation.equals("+")) {
            result = poly1.add(poly2);
        }else if (operation.equals("−")) {
            result = poly1.subtract(poly2);
        }else if (operation.equals("×")) {
            result = poly1.multiply(poly2);
        }else {
            resultArea.setText("div not avaliable now ");
            return; }
        
        result.sortDescending();

        // display ways
        String display;
        if (prefixRadio.isSelected()) {
            display = PolishConvert.toPrefix(result);
        } else if (postfixRadio.isSelected()) {
            display = PolishConvert.toPostfix(result);
        } else {
            display = result.toString(); // default displays
        }

        // show answer
        resultArea.setText(display);

    } catch (Exception ex) {
        resultArea.setText("Error: Unable to parse the equation. Please check the format.");
  
    }
});
        evaluateBtn.addActionListener(e -> {
    try {
        // confirem there an ansewer befor exchange value
        String input1 = eqField1.getText();
        String input2 = eqField2.getText();
        String xText = xValueField.getText().trim();

        if (input1.isEmpty() || input2.isEmpty()) {
            resultArea.setText("input equation first !!.");
            return;}
        if (xText.isEmpty()) {
            resultArea.setText("to evalute please inter value ");
            return;}
        int x = Integer.parseInt(xText);

        PolynomialLinkedList poly1 = EquParser.parse(input1);
        PolynomialLinkedList poly2 = EquParser.parse(input2);

        String op = (String) operationBox.getSelectedItem();
        PolynomialLinkedList result;

        if (op.equals("+")) result = poly1.add(poly2);
        else if (op.equals("−")) result = poly1.subtract(poly2);
        else if (op.equals("×")) result = poly1.multiply(poly2);
        else {
            resultArea.setText("division not avaliable.");
            return;
        }
        result.sortDescending();

        int output = result.evaluate(x);

        resultArea.setText(" X = " + x + "\n answer is : " + output);

    } catch (NumberFormatException ex) {
        resultArea.setText("Please enter an integer vaule for X.");
    } catch (Exception ex) {
        resultArea.setText("An error occurred during evaluation\n" + ex.getMessage());
    }
});
    //reset clearbtn
    clearBtn.addActionListener(e -> {
    eqField1.setText("");
    eqField2.setText("");
    xValueField.setText("");
    resultArea.setText("");
    infixRadio.setSelected(true);
});

       
}}


