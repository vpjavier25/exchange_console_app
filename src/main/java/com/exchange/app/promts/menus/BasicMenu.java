package com.exchange.app.promts.menus;


import java.util.Scanner;

public class BasicMenu extends Menu {

    public BasicMenu(String[] options, String question) {
        this.options = options;
        this.question = question;
    }

    public BasicMenu() {}


    @Override
    public int display() throws NumberFormatException {
        super.setTitle();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        int selection;


        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Indica la opción deseada: ");
            String text = in.nextLine().trim();
            if (text.isEmpty()) {
                return -1;
            }
            try {
                selection = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number: " + text);
                continue;
            }
            if (selection < 1) {
                return selection == -1 ? -1 : 0;
            } else if (selection > options.length - 1) {
                return -1;
            }
            return selection - 1;
        }
    }
}
