package ru.ideaplatform;

import java.io.File;

public class FileParsingException extends RuntimeException {
    public  FileParsingException(File file, Throwable cause) {
        super("Can`t read file " + file.getAbsolutePath(), cause);
    }
}
