package ru.app.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.app.application.entity.Note;

@Data
@AllArgsConstructor
public class SimpleNote {
    private String title;
    private String content;

    public Note toNote() {
        return new Note(title, content);
    }
}
