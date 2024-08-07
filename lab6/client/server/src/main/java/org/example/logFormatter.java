package org.example;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class logFormatter extends Formatter {
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getLevel()).append(':');
        sb.append(record.getMessage()).append('\n');
        return sb.toString();
    }
}
