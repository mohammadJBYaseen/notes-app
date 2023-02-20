package com.yaseen.notesapp.exception;

public class ModifyPublicNoteIsNotAllowedException extends AbstractNoteException {

    public ModifyPublicNoteIsNotAllowedException() {
    }

    public ModifyPublicNoteIsNotAllowedException(String message) {
        super(message);
    }

    public ModifyPublicNoteIsNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModifyPublicNoteIsNotAllowedException(Throwable cause) {
        super(cause);
    }

    public ModifyPublicNoteIsNotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
