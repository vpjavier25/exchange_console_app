package com.exchange.app.promts.checkers;

import java.io.IOException;
import java.util.InputMismatchException;

public interface ContinuityChecker {

    int stop() throws InputMismatchException, IOException;

}
