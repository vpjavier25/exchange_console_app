package com.exchange.app.promts.inputs;

import com.exchange.app.utils.Screen;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextInput {
    private final String message;

    public  TextInput(String message){
        this.message = message;
    }

    public String execute() throws InputMismatchException {

        displayTitle();

        System.out.println(message);

        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private void displayTitle (){
        String title = """
                ============================================
                Ingrese la cantidad deseada 
                ============================================
                """;
        System.out.println(title);
    }
}
