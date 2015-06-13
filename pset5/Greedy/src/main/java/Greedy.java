import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Greedy {

    CalculatorFactory calcFactory;

    public Greedy (CalculatorFactory calcFactory) {
        this.calcFactory = calcFactory;
    }

    public static void main(String[] varArgs) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        Greedy greedy = (Greedy) context.getBean("calc");

        String amountOfChange;

        if (varArgs.length < 1) {

            Scanner input = new Scanner(System.in);

            System.out.println("Please enter the amount of change needed. Ex: $4.51 or €86.72");

            amountOfChange = input.next();

            while (inputIsValid(amountOfChange)[0] < 0) amountOfChange = input.next();

        } else amountOfChange = varArgs[0];

        String cur = getCurrency(amountOfChange);

        CoinCalculator calc;

        if (cur.equals("dollars")) calc = greedy.calcFactory.dollarCalculator(inputIsValid(amountOfChange));
        else calc = greedy.calcFactory.euroCalculator(inputIsValid(amountOfChange));

        calc.print();

    }

    public static int[] inputIsValid(String amountOfChange) {

        if (!getCurrency(amountOfChange).equals("dollars") && !getCurrency(amountOfChange).equals("euros")) {
            System.out.println("Please precede your amount with a $ or € sign.");
            return new int[] {-1, -1};
        } else {
            String[] parts = amountOfChange.split(Pattern.quote("."));
            if (parts.length == 2 && parts[1].length() == 2) return new int[] {Integer.parseInt(parts[0].substring(1)), Integer.parseInt(parts[1])};
            else {
                System.out.println("Please enter a valid dollar or Euro amount. Ex: $27.13 or €3.00");
                return new int[] {-1, -1};
            }
        }

    }

    public static String getCurrency (String amountOfChange) {

        String s = amountOfChange.substring(0, 1);

        if (s.equals("$")) return "dollars";
        else if (s.equals("€")) return "euros";

        return  "unrecognized currency";

    }

}
