package com.exchange.app.utils;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

public class AnsiSupport {
    public static boolean checkAnsiSupport() {
        try (Terminal terminal = TerminalBuilder.builder().system(true).build()) {
            return terminal.getStringCapability(InfoCmp.Capability.set_a_foreground) != null
                    && terminal.getStringCapability(InfoCmp.Capability.set_a_background) != null && System.console() != null;
        } catch (Exception e) {
            return false;
        }
    }
}
