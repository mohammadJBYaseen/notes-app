package com.yaseen.notesapp.exception;

public class NoteNotFoundException  extends AbstractNoteException{
    public NoteNotFoundException() {
    }

    public NoteNotFoundException(String message) {
        super(message);
    }

    public NoteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteNotFoundException(Throwable cause) {
        super(cause);
    }

    public NoteNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
