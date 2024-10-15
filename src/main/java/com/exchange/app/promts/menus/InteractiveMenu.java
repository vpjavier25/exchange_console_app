package com.exchange.app.promts.menus;

import com.exchange.app.utils.Screen;
import org.fusesource.jansi.Ansi;
import org.jline.keymap.BindingReader;
import org.jline.keymap.KeyMap;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.io.PrintWriter;

public class InteractiveMenu extends Menu {

    public InteractiveMenu(String[] options, String question) {
        this.options = options;
        this.question = question;
    }

    public InteractiveMenu() {}


    @Override
    public int display() throws IOException {
        Screen.cleanScreen();
        System.out.print("\u001B[?25l");
        int selection = 0;
        try (Terminal term = TerminalBuilder.builder().system(true).build()) {
            term.enterRawMode();
            PrintWriter writer = term.writer();
            KeyMap<String> keyMap = new KeyMap<>();
            BindingReader bindingReader = new BindingReader(term.reader());

            keyMap.setAmbiguousTimeout(500);
            keyMap.bind("up", "\u001b[A");
            keyMap.bind("down", "\u001b[B");
            keyMap.bind("exit", KeyMap.ctrl('c'));
            keyMap.bind("enter", "\r");

            super.setTitle();
            writer.println(Ansi.ansi().fgGreen().a("? ").reset().a(question).toString());
            writer.println("\r");
            for (int i = 0; i < options.length; i++) {
                String leftHand = i == 0 ? " > " : "   ";
                writer.println(leftHand
                        +(Ansi.ansi().fg(Ansi.Color.GREEN).a(i + 1).reset().toString())
                        + ". "
                        + (selection == i ? (Ansi.ansi().fg(Ansi.Color.CYAN).a(options[i]).reset().toString()) : options[i]));
            }
            writer.flush();

            while (true) {
                int prevSelection = selection;
                String key = bindingReader.readBinding(keyMap);

                switch (key) {
                    case "up":
                        selection--;
                        if (selection == -1) {
                            selection = options.length - 1;
                        }
                        break;
                    case "down":
                        selection++;
                        if (selection == options.length) {
                            selection = 0;
                        }
                        break;
                    case "enter":
                        System.out.print("\u001B[?25h");
                        return selection == options.length - 1 ? -1 : selection;
                    case "exit":
                        System.out.print("\u001B[?25h");
                        return -1;
                }
                printUp(writer, "   ", options.length  - prevSelection, false);
                printUp(writer, " > ", options.length  - selection, true);
            }
        } catch (IOException e) {
            throw new IOException();
        }


    }

    private void printUp(PrintWriter writer, String cursor, int pos, boolean actualSelection) {
        int selection = options.length - pos;
        String option;
        if (actualSelection) {
            option = cursor + (Ansi.ansi().fg(Ansi.Color.GREEN).a(selection + 1).reset().toString()) + ". "
                    + (selection < options.length
                    ? Ansi.ansi().fg(Ansi.Color.CYAN).a(options[selection]).reset().toString()
                    : Ansi.ansi().fg(Ansi.Color.RED).a(options[options.length - 1]).reset().toString());
        } else {
            option = cursor +
                    (Ansi.ansi().fg(Ansi.Color.GREEN).a(selection + 1).reset().toString())
                    + ". " + (selection < options.length
                    ? options[selection]
                    : options[options.length - 1]);
        }

        writer.write(
                Ansi.ansi()
                        .cursorUp(pos)
                        .a(option)
                        .cursorDown(pos)
                        .cursorLeft(option.length())
                        .reset()
                        .toString()
        );
        writer.flush();
    }
}
