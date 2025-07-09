package ru.app.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.app.application.dto.SimpleNote;
import ru.app.application.entity.Note;
import ru.app.application.service.NoteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("add")
    public ResponseEntity<Note> addNote(@RequestBody SimpleNote note) {
        return ResponseEntity.ok(noteService.save(note.toNote()));
    }

    @GetMapping("list")
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable long id) {
        try {
            Note lookingNote = noteService.getNoteById(id).get();
            return ResponseEntity.ok(lookingNote);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Note> updateNote(@PathVariable long id, @RequestBody SimpleNote note) {
        Optional<Note> lookingNote = noteService.getNoteById(id);
        if (lookingNote.isPresent()) {
            lookingNote.get().setTitle(note.getTitle());
            lookingNote.get().setContent(note.getContent());
            return ResponseEntity.ok(noteService.save(lookingNote.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable long id) {
        Optional<Note> deletingNote = noteService.getNoteById(id);
        if (deletingNote.isPresent()) {
            noteService.deleteNote(deletingNote.get().getId());
            return ResponseEntity.ok(deletingNote.get());
        }
        return ResponseEntity.notFound().build();
    }
}
