import java.util.Scanner;

public class Mario {

    public static void main (String[] args) {

        Scanner input = new Scanner(System.in);

        int height = -1;

        do {

            System.out.println("Please enter an integer between 0 and 23...");

            while (!input.hasNextInt()) {
                System.out.println("Please enter an integer between 0 and 23...");
                input.next();

            }

            height = input.nextInt();

        } while (height < 0 || height > 23);

        int row = 1;

        while (row <= height) {

            for (int i = 0; i <= height; i++) {

                if (i < height-row) System.out.print(" ");
                else System.out.print("#");

            }

            System.out.print("\n");
            row++;

        }

    }

}
