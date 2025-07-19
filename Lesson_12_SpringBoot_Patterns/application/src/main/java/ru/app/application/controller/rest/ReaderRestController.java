package ru.app.application.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.app.application.entity.Reader;
import ru.app.application.service.ReaderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reader")
public class ReaderRestController {

    private final ReaderService readerService;

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReaderById(@PathVariable("id") long id) {
        Optional<Reader> reader = readerService.getReaderById(id);
        return reader.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Reader>> getAllReaders() {
        List<Reader> readersList = readerService.getReaders();
        if (!readersList.isEmpty()) {
            return ResponseEntity.ok(readersList);
        }
        return ResponseEntity.notFound().build();
    }
}
