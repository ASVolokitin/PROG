package org.example.interfaces;

import java.io.FileNotFoundException;

public interface Executable {

    /**
     * Интерфейс для исполняемых классов.
     * @param arg
     * @throws FileNotFoundException
     */

    void execute(String arg) throws FileNotFoundException;
}
