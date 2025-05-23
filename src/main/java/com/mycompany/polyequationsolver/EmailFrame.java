package com.mycompany.polyequationsolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmailFrame extends JFrame {

    private JTextField emailField;
    private JButton confirmbtn;

    public EmailFrame() {
        setTitle("Math Equation Solver - Login");
        setSize(500, 200); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setResizable(false);

        
        getContentPane().setBackground(new Color(255, 228, 232)); // Light pink background

        // layout 
        setLayout(new FlowLayout(FlowLayout.LEFT, 25, 20));

        //label
        JLabel emailLbl = new JLabel("Enter your Email:");
        emailLbl.setForeground(new Color(255, 105, 180)); 
        emailLbl.setFont(new Font("SansSerif", Font.BOLD, 20)); 

        //textFaild
        emailField = new JTextField("e.g., user@example.com", 30); 
        emailField.setForeground(new Color(130, 130, 130)); 
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // confirm botton
        confirmbtn = new JButton("Confirm");
        confirmbtn.setBackground(new Color(0, 180, 0)); 
        confirmbtn.setForeground(Color.WHITE);
        confirmbtn.setFont(new Font("SansSerif", Font.BOLD, 14));

        // Placeholder behavior
        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals("e.g., user@example.com")) {
                    emailField.setText("");
                }
                emailField.setForeground(Color.BLACK); 
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().trim().isEmpty()) {
                    emailField.setText("e.g., user@example.com");
                    emailField.setForeground(new Color(130, 130, 130));
                }
            }
        });

        // check Email confirm
       confirmbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                if (isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Email accepted. Welcome!");
                    dispose();
                    new EquationMathFrame(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email address!", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //add component
        add(emailLbl);
        add(emailField);
        add(confirmbtn);

        setVisible(true);
    }
//check vaild email
    private boolean isValidEmail(String email) {
     if (email.equals("e.g., user@example.com")) return false; //reject default text
    if (!email.contains("@") || !email.contains(".")|| email.isEmpty()) return false;
    int a = email.indexOf("@");
    int dot = email.lastIndexOf(".");
    return a > 0 && dot > a + 1 && dot < email.length() - 1;
}

}
