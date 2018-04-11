package poly;

public class Rational {

    private int num; // the numerator
    private int den; // the denominator

    /**
     * Given a numerator and a denominator 
     * make a rational number of the form
     * <br/>
     * numerator
     * <br/>
     * ---------
     * <br/>
     * denominator.
     * 
     * <br/>
     * Initialize the rational number, throws an ArithmeticException.
     */
    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator should't be zero.");
        } else {
            num = numerator;
            den = denominator;
        }
    }

    /**
     * Get the numerator
     * 
     * @param num
     *            The numerator
     * @return numerator
     */
    public int get_numerator() {
        return num;
    }

    /**
     * Get the denominator
     * 
     * @param den
     *            The denominator
     * @return denominator
     */
    public int get_denominator() {
        return den;
    }

    /**
     * Do summation for two rational numbers
     * 
     * @param other
     *            Another rational number
     * @return summ The summation of two rational numbers
     */
    public Rational add(Rational other) {
        int sum_num = this.get_numerator() * other.get_denominator()
                + this.get_denominator() * other.get_numerator();
        int sum_den = this.get_denominator() * other.get_denominator();
        Rational summ = new Rational(sum_den, sum_num);
        return summ;
    }

    /**
     * Do subtraction for two rational numbers
     * 
     * @param other
     *            Another rational number
     * @return subt The subtraction of two rational numbers
     */
    public Rational sub(Rational other) {
        int sub_num = this.get_numerator() * other.get_denominator()
                - this.get_denominator() * other.get_numerator();
        int sub_den = this.get_denominator() * other.get_denominator();
        Rational subt = new Rational(sub_num, sub_den);
        return subt;
    }

    /**
     * Do multiplication for two rational numbers
     * 
     * @param other
     *            Another rational number
     * @return multi The multiplication of two rational numbers
     */
    public Rational mul(Rational other) {
        int mul_num = this.get_numerator() * other.get_numerator();
        int mul_den = this.get_denominator() * other.get_denominator();
        Rational multi = new Rational(mul_num, mul_den);
        return multi;
    }

    /**
     * Do division for two rational numbers
     * 
     * @param other
     *            Another rational number
     * @return divi The division of two rational numbers
     */
    public Rational div(Rational other) {
        int div_num = this.get_numerator() * other.get_denominator();
        int div_den = this.get_denominator() * other.get_numerator();
        Rational divi = new Rational(div_num, div_den);
        return divi;
    }

    /**
     * Do square of the rational number
     * 
     * @return squa The square of the rational number
     */
    public Rational sqr() {
        return this.mul(this);
    }

    /** Cancel out common factors between denominator and denominator */
    public void reduceToLowestForm() {
        int maxPossibleFactor;
        if (Math.abs(this.num) < Math.abs(this.den)) {
            maxPossibleFactor = Math.abs(this.num);
        } else {
            maxPossibleFactor = Math.abs(this.den);
        }
        for (int i = 2; i <= maxPossibleFactor; i++) {
            if ((this.num % i == 0) && (this.den % i == 0)) {
                this.num = this.num / i;
                this.den = this.den / i;
            }
        }
    }

    /**
     * Display number as a string
     * 
     * @return The string form of the number
     */
    public String toString() {
        if (this.den == 1) {
            return Integer.toString(this.num);
        } else {
            if (this.num == 0)
                return "0";
            else {
                return Integer.toString(this.num) + "/"
                        + Integer.toString(this.den);
            }

        }
    }

    /**
     * Give conversion of the rational number to decimals
     * 
     * @return deci The decimal form of the rational number
     */
    public double decimal() {
        double deci =  this.num / this.den;
        return deci;
    }

    /**
     * This is the main method
     */
    public static void main(String[] args) {
        Rational r = new Rational(1, 4);
        Rational r1 = new Rational(1, 4);
        Rational r2 = r.add(r1);
        r2.reduceToLowestForm();
        System.out.println(r1 + " add to " + r + " = " + r2);
        Rational r3 = new Rational(2, 3);
        Rational r4 = new Rational(3, 2);
        Rational r5 = r3.mul(r4);
        r5.reduceToLowestForm();
        System.out.println(r5);
        Rational r7 = new Rational(0, 1);
        System.out.println(r7.get_numerator());
    }
}
