package com.mycompany.polyequationsolver;

public class EquParser {

    // convert a polynomial string into a list of terms (PolynomialLinkedList)
   
    public static PolynomialLinkedList parse(String expr) {
        PolynomialLinkedList result = new PolynomialLinkedList();
        expr = expr.replace(" ", ""); // delete spaces

        int i = 0;
        while (i < expr.length()) {
            char sign = '+';         // default sign of the current term 
            if (expr.charAt(i) == '+' || expr.charAt(i) == '-') {
                sign = expr.charAt(i);
                i++;
            }
      try{
            // take coeficient (befor x )
            StringBuilder num = new StringBuilder();
            while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                num.append(expr.charAt(i));
                i++; // move past 'X'
            }

            int factor = num.length() > 0 ? Integer.parseInt(num.toString()) : 1;
            if (sign == '-') factor *= -1;

            int degree = 0;

            // if finde X
            if (i < expr.length() && Character.toLowerCase(expr.charAt(i)) == 'x') {
                degree = 1; 
                i++;

                // if there ^ after x 
                if (i < expr.length() && expr.charAt(i) == '^') {
                    i++; // Skip^
                    StringBuilder pow = new StringBuilder();
                    while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                        pow.append(expr.charAt(i));
                        i++;
                    }
                    if (pow.length() > 0) {
                        degree = Integer.parseInt(pow.toString());
                    }else {
                            throw new IllegalArgumentException("there is no num after ^ "+ i);
                }} }   
            
                 if (i < expr.length()) {
                    char current = expr.charAt(i);
                    if (!Character.isDigit(current) && Character.toLowerCase(current) != 'x' &&
                        current != '+' && current != '-' && current != '^') {
                        throw new IllegalArgumentException(" unexpect Digigt: '" + current + "'at this place " + i);
                    }
                }

           // add the parsed term (coefficient and pow) to the polynomial list>>>linked list
            result.addTerm(factor, degree);
        }catch (NumberFormatException e) {
            System.out.println("error during parsing: " + e.getMessage());
                break;
       }catch(IllegalArgumentException e) {
                System.out.println("!! " + e.getMessage());
                break;
            }
     }
        return result;
    }}
//.....................................................................................

       

            
          
            
         
