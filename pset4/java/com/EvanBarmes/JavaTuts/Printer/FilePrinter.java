package com.EvanBarmes.JavaTuts.Printer;

import com.EvanBarmes.JavaTuts.Pyramid;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter implements Printer {

    public void FilePrinter () {

    }

    public void Print(Pyramid pyramid) {

        File file = new File("Mario.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()))) {

            bw.write(pyramid.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
