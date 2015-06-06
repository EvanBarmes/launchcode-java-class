package com.EvanBarmes.JavaTuts.Printer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter implements Printer {

    public void FilePrinter () {

    }

    public void Print(String str) {

        File file = new File("Mario.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()))) {

            bw.write(str);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
