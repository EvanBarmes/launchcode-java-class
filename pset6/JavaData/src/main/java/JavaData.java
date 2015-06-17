import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Scanner;

public class JavaData {

    SQLiteJDBC db;

    public JavaData (SQLiteJDBC db) {
        this.db = db;
    }

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        JavaData jd = (JavaData) context.getBean("jd");

        jd.Run();

    }

    public void Run() {

        int totalStops = db.listStops();

        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the # for your current station.");

        String num = input.next();

        while (usable(num, totalStops) == -1) num = input.next();

        System.out.println(db.nextTrainAt(Integer.parseInt(num), Calendar.getInstance().getTime()));

    }

    public static int usable (String str, int limit) {

        try {
            int num = Integer.parseInt(str);
            if (num < limit && num > -1) return num;
            else {
                System.out.println("\nPlease enter a valid stop number.");
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nPlease enter a valid stop number.");
            return -1;
        }

    }

}
