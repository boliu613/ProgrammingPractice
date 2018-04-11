package poly;

import java.util.*;


public class Polynomial {

    // 2/3 x^2 + 4/5 x^1 + 1 is represented as {2: Rational(2,3), 1:
    // Rational(4,5), 0: Rational(1,1)}
    // The key (the integer part) is the power of x
    HashMap<Integer, Rational> coefficients;

    /**
     * Create a new polynomial with a HashMap of coefficients
     * 
     * @param coefficient
     */
    public Polynomial(HashMap<Integer, Rational> coefficients) {
        this.coefficients = coefficients;
    }

    /**
     * Initialize the polynomial with a list of coefficients in order - constant
     * term, linear term, square term, etc etc Assumption is the first term in
     * the list is for the const, second for the linear term third for x^2 and
     * so on
     * 
     * @param coeffs
     */
    public Polynomial(ArrayList<Rational> coeffs) {
        coefficients = new HashMap<Integer, Rational>();
        for (int i = 0; i <= coeffs.size(); i++) {
            coefficients.put(i, coeffs.get(i));
        }
    }

    /**
     * main function to be filled in at the end
     * 
     * @param args
     */
    public static void main(String[] args) {
        // see specs of the assignment
        // you have more freedom about what you do here
        System.out.println("Welcome to use polynomial caculator!");

        // get the first polynomial
        System.out.println("Please enter the degree of the first polynomial.");
        Scanner scanner = new Scanner(System.in);
        String coeff;
        int degree1 = scanner.nextInt();
        HashMap<Integer, Rational> poly1Coeff = new HashMap<Integer, Rational>();
        Polynomial poly1 = new Polynomial(poly1Coeff);
        for (int i = 1; i <= degree1; i++) {
            System.out.println("Please enter the coefficient of x^"
                    + (degree1 - i + 1));
            coeff = scanner.next();
            poly1Coeff.put(degree1 - i + 1, poly1.splitAboutSlash(coeff));
        }
        System.out.println("Please enter the constant");
        coeff = scanner.next();
        poly1Coeff.put(0, poly1.splitAboutSlash(coeff));
        poly1 = new Polynomial(poly1Coeff);

        // get the second polynomial
        System.out.println("Please enter the degree of the second polynomial.");
        int degree2 = scanner.nextInt();
        HashMap<Integer, Rational> poly2Coeff = new HashMap<Integer, Rational>();
        Polynomial poly2 = new Polynomial(poly2Coeff);
        for (int i = 1; i <= degree2; i++) {
            System.out.println("Please enter the coefficient of x^"
                    + (degree2 - i + 1));
            coeff = scanner.next();
            poly2Coeff.put(degree2 - i + 1, poly2.splitAboutSlash(coeff));
        }
        System.out.println("Please enter the constant");
        coeff = scanner.next();
        poly2Coeff.put(0, poly2.splitAboutSlash(coeff));
        poly2 = new Polynomial(poly2Coeff);

        // do caculation
        Polynomial resultPoly;
        System.out.println("What do you want to do with the polynomial? \n "
                + "'a' for add. \n " + "'s' for sub. \n "
                + "'m' for multiply. \n " + "'q' for quit.");
        switch (scanner.next()) {
        case "a":
            resultPoly = poly1.add(poly2);
            System.out.println("The summation of the two polynomials is "
                    + resultPoly);
            break;
        case "s":
            resultPoly = poly1.sub(poly2);
            System.out.println("The subtraction of the two polynomials is "
                    + resultPoly);
            break;
        case "m":
            resultPoly = poly1.multiply(poly2);
            System.out.println("The multiplication of the two polynomials is "
                    + resultPoly);
            break;
        case "q":
            System.out.println("Thanks for using polynomial caculator!");
            break;
        }
        scanner.close();
    }

    /**
     * get the coefficient of x^power
     * 
     * @param power
     * @return
     */
    public Rational getCoefficient(int power) {
        if (coefficients.get(power) == null)
            return (new Rational(0, 1));
        else
            return coefficients.get(power);
    }

    /**
     * add the secondPolynomial to the current polynomial and return a new
     * Polynomial
     * 
     * @param secondPolynomial
     * @return
     */
    public Polynomial add(Polynomial secondPolynomial) {
        int maxDegree = Math.max(this.degree(), secondPolynomial.degree());
        HashMap<Integer, Rational> sumCoefficients = new HashMap<Integer, Rational>();
        for (int i = 0; i <= maxDegree; i++) {
            Rational coeff = this.getCoefficient(i).add(
                    secondPolynomial.getCoefficient(i));
            coeff.reduceToLowestForm();
            sumCoefficients.put(i, coeff);
        }
        Polynomial sumPoly = new Polynomial(sumCoefficients);
        return sumPoly;
    }

    /**
     * subtract one polynomial from the other
     * 
     * @param secondPolynomial
     * @return
     */
    public Polynomial sub(Polynomial secondPolynomial) {
        HashMap<Integer, Rational> negCoefficients = new HashMap<Integer, Rational>();
        for (int key : secondPolynomial.coefficients.keySet()) {
            Rational negCoeff = secondPolynomial.coefficients.get(key).mul(
                    new Rational(-1, 1));
            negCoefficients.put(key, negCoeff);
        }
        Polynomial negSecondPolynomial = new Polynomial(negCoefficients);
        Polynomial subPoly = this.add(negSecondPolynomial);
        return subPoly;
    }

    /**
     * multiply two polynomials the current polynomial (this) and the
     * secondPolynomial as shown in lab/
     * 
     * @param secondPolynomial
     * @return
     */
    public Polynomial multiply(Polynomial secondPolynomial) {
        HashMap<Integer, Rational> multCoefficients = new HashMap<Integer, Rational>();
        Polynomial multPoly = new Polynomial(multCoefficients);
        for (int key1 : secondPolynomial.coefficients.keySet()) {
            HashMap<Integer, Rational> tempCoefficients = new HashMap<Integer, Rational>();
            for (int key2 : this.coefficients.keySet()) {
                Rational tempCoeff = this.getCoefficient(key2).mul(
                        secondPolynomial.getCoefficient(key1));
                tempCoeff.reduceToLowestForm();
                tempCoefficients.put(key1 + key2, tempCoeff);
            }
            Polynomial tempPoly = new Polynomial(tempCoefficients);
            multPoly = multPoly.add(tempPoly);
        }
        return multPoly;
    }

    /**
     * return the degree of polynomial the degree of the polynomial is defined
     * as the maximum power that has a non-zero coefficient.
     * 
     * @return
     */
    public int degree() {
        /**
         * This is Version 1 ArrayList<Integer> powers = new
         * ArrayList<Integer>(); for (int key: coefficients.keySet()){
         * powers.add(key); } Collections.sort(powers);
         * Collections.reverse(powers); if (powers.size() == 0) return 0; else
         * return powers.get(0);
         */
        int power = 0;
        if (coefficients.size() != 0) {
            for (int key : coefficients.keySet()) {
                if (key > power)
                    power = key;
            }
        }
        return power;
    }

    /**
     * simplify the polynomial by deleting zero coefficient terms since it's not
     * necessary to store zero coefficient terms in memory
     */
    public void simplifyPoly() {
        HashMap<Integer, Rational> newCoefficients = new HashMap<Integer, Rational>();
        for (int key : coefficients.keySet()) {
            if (coefficients.get(key).get_numerator() != 0) {
                newCoefficients.put(key, coefficients.get(key));
            }
        }
        coefficients = newCoefficients;
    }

    public HashMap<Integer, Rational> getCoefficients() {
        return coefficients;
    }

    /**
     * Use this function to do the splitting of the user input
     * 
     * @param s
     * @return
     */
    public Rational splitAboutSlash(String s) {
        String[] numDen = s.split("/");
        if (numDen.length < 2){
        	return new Rational(Integer.parseInt(s), 1);
        }
        int numerator = Integer.parseInt(numDen[0]);
        int denominator = Integer.parseInt(numDen[1]);
        return new Rational(numerator, denominator);
    }

    /**
     * returns the polynomial in the form 2/3 x^2 -1/3 x^1 + 7
     */
    public String toString() {
        this.simplifyPoly();
        String polyString;
        TreeMap<Integer, Rational> sortedCoeff = new TreeMap<Integer, Rational>(
                coefficients);
        if (sortedCoeff.size() == 0)
            return "0";
        else {
            Map.Entry<Integer, Rational> term1 = sortedCoeff.pollLastEntry();
            if (term1.getKey() == 0)
                polyString = term1.getValue().toString();
            else
                polyString = term1.getValue().toString() + " x^"
                        + term1.getKey().toString();
            while (sortedCoeff.size() != 0) {
                Map.Entry<Integer, Rational> term = sortedCoeff.pollLastEntry();
                if (term.getKey() == 0) {
                    if (term.getValue().get_numerator() < 0)
                        polyString += " " + term.getValue().toString();
                    else
                        polyString += " + " + term.getValue().toString();
                } else {
                    if (term.getValue().get_numerator() < 0)
                        polyString += " " + term.getValue().toString() + " x^"
                                + term.getKey().toString();
                    else
                        polyString += " + " + term.getValue().toString()
                                + " x^" + term.getKey().toString();
                }
            }
            return polyString;
        }
    }
}