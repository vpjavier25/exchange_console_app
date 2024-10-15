package com.exchange.app.promts.menus;

public abstract class Menu {
    protected  String[] options;
    protected String question;

    public abstract int display() throws Exception;

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    protected void setTitle(){
        String title = """
                ============================================
                Seleccione una opción una opción
                ============================================
                """;

        System.out.println(title);
    }
}
