
package com.mycompany.polyequationsolver;

public class PolynomialLinkedList {
        private class Node {
        PolyTerm term;
        Node next;

        Node(PolyTerm term) {
            this.term = term;
            this.next = null;
        }
        
        
    }
    private Node head;
      public PolynomialLinkedList() {
        this.head = null;
    }
      
      
    /* Adds a new term to the polynomial in descending order of pow.
        ->if a term with the same pow exists >>>their coefficients are combined.
        ->if the resulting coefficient is 0 >>>  term  removed.*/

      public void addTerm(int coef, int pow) {
    if (coef == 0) return;

    PolyTerm newTerm = new PolyTerm(coef, pow);
    Node newNode = new Node(newTerm);

    if (head == null) {
        head = newNode;
        return;
    }

    if (pow > head.term.getPow()) {
        newNode.next = head;
        head = newNode;
        return;
    }

    Node curr= head;
    Node prev = null;

    while (curr != null && pow < curr.term.getPow()) {
        prev = curr;
        curr = curr.next;
    }

    if (curr != null && pow == curr.term.getPow()) {
        int newCoef = curr.term.getCoef() + coef;
        if (newCoef == 0) {
            if (prev == null) {
                head = curr.next;
            } else {
                prev.next = curr.next;
            }
        } else {
            curr.term.setCoef(newCoef);
        }
        return;
    }

    newNode.next = curr;
    if (prev != null) {
        prev.next = newNode;
    }
    
}
      /* Turns the polynomial linked list into a readable string.*/
      @Override
public String toString() {
    if (head == null) return "0";
    StringBuilder sb = new StringBuilder();
    Node temp = head;

    while (temp != null) {
        int coef = temp.term.getCoef();
        int pow = temp.term.getPow();

        if (coef == 0) {              
            temp = temp.next;
            continue;      // skips any terms that are zero
        }

        if (coef > 0 && temp != head) sb.append("+");

        if (pow == 0) {
            sb.append(coef);
        } else if (pow == 1) {
            if (coef == 1) sb.append("X");
            else if (coef == -1) sb.append("-X");
            else sb.append(coef + "X");
        } else {
            if (coef == 1) sb.append("X^" + pow);
            else if (coef == -1) sb.append("-X^" + pow);
            else sb.append(coef + "X^" + pow);
        }

        temp = temp.next;
    }

    return sb.toString();
}

// methods adds two polynomials together.

public PolynomialLinkedList add(PolynomialLinkedList other) {
    PolynomialLinkedList result = new PolynomialLinkedList();

    Node temp1 = this.head;
    Node temp2 = other.head;

    while (temp1 != null) {
        result.addTerm(temp1.term.getCoef(), temp1.term.getPow());
        temp1 = temp1.next;
    }

    while (temp2 != null) {
        result.addTerm(temp2.term.getCoef(), temp2.term.getPow());
        temp2 = temp2.next;
    }

    return result;
}

//  subtracts one polynomial from another.
public PolynomialLinkedList subtract(PolynomialLinkedList other) {
    PolynomialLinkedList result = new PolynomialLinkedList();

    Node temp1 = this.head;
    Node temp2 = other.head;

    while (temp1 != null) {
        result.addTerm(temp1.term.getCoef(), temp1.term.getPow());
        temp1 = temp1.next;
    }

    while (temp2 != null) {
        result.addTerm(-temp2.term.getCoef(), temp2.term.getPow());
        temp2 = temp2.next;
    }

    return result;
}

//multiplies two polynomials together.
public PolynomialLinkedList multiply(PolynomialLinkedList other) {
    PolynomialLinkedList result = new PolynomialLinkedList();

    Node temp1 = this.head;

    while (temp1 != null) {
        Node temp2 = other.head;
        while (temp2 != null) {
            int newCoef = temp1.term.getCoef() * temp2.term.getCoef();
            int newPow = temp1.term.getPow() + temp2.term.getPow();
            result.addTerm(newCoef, newPow);
            temp2 = temp2.next;
        }
        temp1 = temp1.next;
    }

    return result;
}
// Evaluates the polynomial for a given x.
public int valueAt(int x) {
    int result = 0;
    Node temp = head;

    while (temp != null) {
        int coef = temp.term.getCoef();
        int pow = temp.term.getPow();
        result += coef * Math.pow(x, pow);
        temp = temp.next;
    }

    return result;
}
public int evaluate(int x) {
    int result = 0;
    Node temp = head;
    while (temp != null) {
        int coef = temp.term.getCoef();
        int pow = temp.term.getPow();
        result += coef * Math.pow(x, pow);
        temp = temp.next;
    }
    return result;
}

public void sortDescending() {
    if (head == null || head.next == null) return;

    for (Node current = head; current != null; current = current.next) {
        for (Node next = current.next; next != null; next = next.next) {
            if (current.term.getPow() < next.term.getPow()) {
                // Swap only  PolyTerm objects (not  nodes)
                PolyTerm temp = current.term;
                current.term = next.term;
                next.term = temp;
            }
        }
    }
}


    
}
