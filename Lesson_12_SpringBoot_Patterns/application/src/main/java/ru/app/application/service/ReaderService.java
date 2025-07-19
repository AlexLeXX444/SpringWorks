package ru.app.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.application.entity.Reader;
import ru.app.application.repository.ReaderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    public Reader addReader(String readerName) {
        return readerRepository.save(new Reader(readerName));
    }

    public Optional<Reader> getReaderById(long id) {
        return readerRepository.findById(id);
    }

    public List<Reader> getReaders() {
        return readerRepository.findAll();
    }

    public boolean deleteReaderById(long id) {
        if (readerRepository.existsById(id)) {
            readerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
