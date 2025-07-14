package ru.app.readerservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.app.readerservice.entity.Reader;
import ru.app.readerservice.service.ReaderService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ReaderService readerService;

    @Override
    public void run(String... args) throws Exception {
        if (readerService.getAllReaders().isEmpty()) {
            List<String> readerNames = new ArrayList<>();
            readerNames.add("Alex");
            readerNames.add("Rolf");
            readerNames.add("Mark");
            readerNames.add("Sherman");
            readerNames.add("Lex");

            for (String s : readerNames) {
                readerService.addReader(new Reader(s));
            }
        }
    }
}
