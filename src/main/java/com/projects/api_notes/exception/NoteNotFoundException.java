package com.projects.api_notes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoteNotFoundException extends RuntimeException {
    private final int status = HttpStatus.NOT_FOUND.value();

    public NoteNotFoundException(Long id) {
        super("ID anotação não localizada: " + id);
    }

    public int getStatus() {
        return status;
    }

}