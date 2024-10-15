package com.exchange.app.promts.checkers;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class TextChecker implements ContinuityChecker {

    public int stop() throws InputMismatchException {



        while (true) {
            System.out.println("? " + "**Desea continuar** (s/n)" + "\n");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine().toLowerCase(Locale.ROOT);
            switch (input) {
                case "s":
                    return 0;
                case "n":
                    return -1;
            }
        }
    }
}