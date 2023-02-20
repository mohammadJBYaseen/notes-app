package com.yaseen.notesapp.exception;

public abstract class AbstractNoteException extends RuntimeException {
    public AbstractNoteException() {
    }

    public AbstractNoteException(String message) {
        super(message);
    }

    public AbstractNoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractNoteException(Throwable cause) {
        super(cause);
    }

    public AbstractNoteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
