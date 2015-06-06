package com.EvanBarmes.JavaTuts;

import com.EvanBarmes.JavaTuts.Printer.ConsolePrinter;
import com.EvanBarmes.JavaTuts.Printer.FilePrinter;
import com.EvanBarmes.JavaTuts.Printer.PrintFactory;
import com.EvanBarmes.JavaTuts.Printer.Printer;

import java.util.Scanner;

public class Mario {

    public static void main (String[] args) {

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

        if (c == 'c') output = PrintFactory.consolePrinter();
        else output = PrintFactory.filePrinter();

        pyramid.BuildPyramid(height);

        output.Print(pyramid);

    }

}
