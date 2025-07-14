package ru.app.readerservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.app.readerservice.dto.ReaderDto;
import ru.app.readerservice.entity.Reader;
import ru.app.readerservice.service.ReaderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reader")
@RequiredArgsConstructor
public class ReaderRestController {

    private final ReaderService readerService;

    @GetMapping("{id}")
    public ResponseEntity<Reader> getReaderById(@PathVariable Long id) {
        Optional<Reader> reader = readerService.getReaderById(id);
        if (reader.isPresent()) {
            return ResponseEntity.ok(reader.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Reader>> getReaders() {
        List<Reader> readers =  readerService.getAllReaders();
        if (readers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(readers);
    }

    @PostMapping("/add")
    public Reader addReader(@RequestBody ReaderDto readerDto) {
        return readerService.addReader(readerDto.toEntity());
    }
}
