
package com.mycompany.polyequationsolver;


public class PolyTerm {
   
    private int coef;  // "coefficient"
    private int pow; 

    public PolyTerm(int coef, int pow) {
        this.coef = coef;
        this.pow = pow;
    }

    public int getCoef() {
        return coef;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    public int getPow() {
        return pow;
    }

    public void setPow(int pow) {
        this.pow = pow;
    }

    @Override
    public String toString() {
        return coef + "X^" + pow;
    }
}

