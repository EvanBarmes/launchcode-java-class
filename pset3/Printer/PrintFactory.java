package com.EvanBarmes.JavaTuts.Printer;

public class PrintFactory {

    public static Printer consolePrinter() {

        return new ConsolePrinter();

    }

    public static Printer filePrinter() {

        return new FilePrinter();

    }

}
