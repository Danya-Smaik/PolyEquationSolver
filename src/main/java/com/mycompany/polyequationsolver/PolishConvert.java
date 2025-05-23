package com.mycompany.polyequationsolver;

import java.util.*;

public class PolishConvert {

    // convert from infix to postfix 
    public static String toPostfix(PolynomialLinkedList poly) {
        String infix = poly.toString();
        List<String> tokens = tokenize(infix);

        StringBuilder postfix = new StringBuilder();
        StringStack ops = new StringStack();

        for (String token : tokens) {
            if (isOperator(token)) {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(token)) {
                    postfix.append(ops.pop()).append(" ");
                }
                ops.push(token);
            } else {
                postfix.append(token).append(" ");
            }
        }

        while (!ops.isEmpty()) {
            postfix.append(ops.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    //  convert from infix to prefix 
    public static String toPrefix(PolynomialLinkedList poly) {
        String infix = poly.toString();
        List<String> tokens = tokenize(infix);
        Collections.reverse(tokens);
// Swap parentheses after reversing
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("(")) tokens.set(i, ")");
            else if (tokens.get(i).equals(")")) tokens.set(i, "(");
        }

        StringStack ops = new StringStack();
        StringStack output = new StringStack();

        for (String token : tokens) {
            if (isOperator(token)) {
                while (!ops.isEmpty() && precedence(ops.peek()) > precedence(token)) {
                    output.push(ops.pop());
                }
                ops.push(token);
            } else {
                output.push(token);
            }
        }

        while (!ops.isEmpty()) {
            output.push(ops.pop());
        }

        List<String> result = new ArrayList<>();
        while (!output.isEmpty()) {
            result.add(output.pop());
        }
        Collections.reverse(result);

        return String.join(" ", result);
    }

    //divide exp into terms or tokens
    private static List<String> tokenize(String expr) {
        List<String> tokens = new ArrayList<>();
        StringBuilder term = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == '+' || c == '-') {
                if (term.length() > 0) {
                    tokens.add(term.toString());
                    term.setLength(0);
                }
                term.append(c);
            } else {
                term.append(c);
                if (i == expr.length() - 1 || expr.charAt(i + 1) == '+' || expr.charAt(i + 1) == '-') {
                    tokens.add(term.toString());
                    term.setLength(0);
                }
            }
        }

        return tokens;
    }

    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
//returns precedence level of an operator
    private static int precedence(String op) {
        if (op.equals("+") || op.equals("-")) return 1;
        if (op.equals("*") || op.equals("/")) return 2;
        return 0;
    }

    // stack class (stack implementation using linked nodes )
    private static class StringStack {

        private class Node {
            String data;
            Node next;

            Node(String data) {
                this.data = data;
            }
        }

        private Node top;

        public void push(String value) {
            Node newNode = new Node(value);
            newNode.next = top;
            top = newNode;
        }

        public String pop() {
            if (isEmpty()) throw new IllegalStateException("Stack is empty");
            String value = top.data;
            top = top.next;
            return value;
        }

        public String peek() {
            if (isEmpty()) throw new IllegalStateException("Stack is empty");
            return top.data;
        }

        public boolean isEmpty() {
            return top == null;
        }
    }
}
