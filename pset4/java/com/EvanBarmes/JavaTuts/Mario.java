package com.EvanBarmes.JavaTuts;

import com.EvanBarmes.JavaTuts.Printer.PrintFactory;
import com.EvanBarmes.JavaTuts.Printer.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Mario {

    PrintFactory printFactory;

    public Mario(PrintFactory printFactory) {

        this.printFactory = printFactory;

    }

    public static void main (String[] args) {

        System.out.println("Thanks for playing \"Mario Pyramid Builder\"!");

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        Mario mario = (Mario) context.getBean("basicMarioGame");

        mario.PlayGame();

    }

    public void PlayGame() {

        Scanner input = new Scanner(System.in);
        Printer output;

        Pyramid pyramid = new Pyramid();

        int height = -1;

        do {

            System.out.println("Please enter an integer between 0 and 23...");

            while (!input.hasNextInt()) {
                System.out.println("Please enter an integer between 0 and 23...");
                input.next();

            }

            height = input.nextInt();

        } while (height < 0 || height > 23);

        char c = 'a';

        while(c != 'c' && c != 'f') {

            System.out.println("Would you like to print to the (c)onsole or a (f)ile?");

            c = input.next().charAt(0);

        }

        if (c == 'c') output = printFactory.consolePrinter();
        else output = printFactory.filePrinter();

        pyramid.BuildPyramid(height);

        output.Print(pyramid);


    }

}
