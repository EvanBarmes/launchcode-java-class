package com.EvanBarmes.JavaTuts.Printer;

import com.EvanBarmes.JavaTuts.Pyramid;

public class ConsolePrinter implements Printer{

    public void ConsolePrinter() {

    }

    public void Print(Pyramid pyramid) {
        System.out.println(pyramid.toString());
    }
}