package org.example.interfaces;

import org.example.requests.Request;
import org.example.responses.Response;

import java.io.FileNotFoundException;

public interface Executable {

    /**
     * Интерфейс для исполняемых классов.
     * @param request
     * @throws FileNotFoundException
     */

    Response execute(Request request) throws FileNotFoundException;
}
