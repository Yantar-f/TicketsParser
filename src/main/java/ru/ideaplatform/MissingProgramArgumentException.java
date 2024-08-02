package ru.ideaplatform;

public class MissingProgramArgumentException extends RuntimeException {
    public MissingProgramArgumentException() {
        super("Program need initial arguments");
    }
}
