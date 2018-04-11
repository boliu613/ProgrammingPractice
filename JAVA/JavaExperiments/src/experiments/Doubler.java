package experiments;

import java.util.ArrayList;
import java.util.Scanner;

public class Doubler {
    
    public static void main(String[] args) {
        //creating an instance of Doubler
        //new Doubler().run();
    	ArrayList<String> languages    	= new ArrayList<String>();
    	languages.add("Python");
    	languages.add("Python");
    	languages.set(0, "Java");
    	languages.set(1, "Java");
    	System.out.println(languages);
    }
    
    private void run() {
        Scanner scanner;
        int number;
        int doubledNumber;
        
        //System.in is the 'console'. 
        //By defining a scanner in this manner, you accept inputs from the user.
        scanner = new Scanner(System.in);
        System.out.println("Enter a number");
        //read the next input as an integer
        number = scanner.nextInt();
        doubledNumber = 2 * number;
        System.out.println("Twice of " + number + " is " + doubledNumber);
        scanner.close();
    }

}
