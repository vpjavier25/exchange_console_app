package com.exchange.app.promts.checkers;

import com.exchange.app.utils.AnsiSupport;
import org.fusesource.jansi.Ansi;
import org.jline.keymap.BindingReader;
import org.jline.keymap.KeyMap;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.io.PrintWriter;

public class keyChecker implements ContinuityChecker {
    @Override
    public int stop() throws Exception {
        try (Terminal term = TerminalBuilder.builder().system(true).build()) {
            term.enterRawMode();
            PrintWriter writer = term.writer();
            writer.println("\r");

            writer.println(Ansi.ansi().fgGreen().a("? ").reset().toString() + "Presiona "
                    + Ansi.ansi().fgGreen().a("ENTER").reset().toString() + " para seguir");


            writer.flush();
            KeyMap<String> keyMap = new KeyMap<>();
            keyMap.bind("enter", "\r");
            keyMap.bind("exit", KeyMap.ctrl('c'));
            BindingReader bindingReader = new BindingReader(term.reader());

            while (true) {
                String key = bindingReader.readBinding(keyMap);
                if (key.equals("enter"))
                    return 0;
            }

        } catch (IOException e) {
            throw new IOException();
        }
    }

}
