package org.example.functionalClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

public class Reader {

    /**
     * Класс, выполняющий чтение данных из консоли и файлов.
     */

    private static File file;
    private static Stack<Scanner> streamStack = new Stack<>();
    private static Scanner currentScanner;
    private static boolean readingConsole = true;
    private static int recursionDepth = 0;

    /**
     * Метод, возвращающий введённые из консоли данные.
     * @return
     */

    /**
     * Метод, возвращающий введённые из файла данные.
     * @return
     */


    /**
     * Метод, определяющий, имеет ли файл со входными данными непрочитанные строки.
     * @return
     */

    public static boolean fileHasNextLine() {
        return currentScanner.hasNextLine();
    }


    public static void finishFile() {
        while (currentScanner.hasNextLine()) {
            String tmp = currentScanner.nextLine();
        }
    }

    public static void addStream(Scanner scanner) {
        streamStack.add(scanner);
        recursionDepth += 1;
        currentScanner = scanner;
        if (streamStack.size() > 1) {
            setReadingConsole(false);
        }
    }

    public static void popStream() {
        streamStack.pop();
        recursionDepth -= 1;
        currentScanner = streamStack.peek();
        if (streamStack.size() <= 1) {
            setReadingConsole(true);
        }
    }

    public static String Read() {
        return currentScanner.nextLine();
    }

    public static boolean isReadingConsole() {
        return readingConsole;
    }

    public static void setReadingConsole(boolean readingConsole) {
        Reader.readingConsole = readingConsole;
    }

    public static void backToConsole() {
        while(streamStack.size() > 1) {
            streamStack.pop();
        }
        currentScanner = streamStack.peek();
        setReadingConsole(true);
        recursionDepth = 0;
    }

    public static int getRecursionDepth() {
        return recursionDepth;
    }
}
