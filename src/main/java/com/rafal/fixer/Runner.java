package com.rafal.fixer;

import java.io.IOException;
import java.io.UncheckedIOException;

public class Runner {

    public static void main(String[] args) {

        TextFixer fixer = new TextFixer();
        try {
            fixer.openFile("D:\\napisy.txt");
            fixer.fixeText();
            fixer.saveFixedFile("D:\\napisyFIXED.txt");
        } catch (IOException e) {
            System.out.println("File not found" + e);
        } catch (UncheckedIOException e) {
            System.out.println("Probably not an UTF-8 file");
        }
    }
}
