package com.EvanBarmes.JavaTuts.Printer;

public class PrintFactory {

    private static PrintFactory printFactory;

    private  PrintFactory() {}

    public static PrintFactory getInstance() {

        if (printFactory == null) printFactory = new PrintFactory();

        return  printFactory;

    }

    public Printer consolePrinter() {

        return new ConsolePrinter();

    }

    public Printer filePrinter() {

        return new FilePrinter();

    }

}
