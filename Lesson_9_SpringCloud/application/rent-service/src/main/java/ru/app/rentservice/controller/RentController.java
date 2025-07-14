package ru.app.rentservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.app.rentservice.restclient.BookClient;
import ru.app.rentservice.restclient.ReaderClient;
import ru.app.rentservice.service.RentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
public class RentController {

    private final RentService rentService;
    private final ReaderClient readerClient;
    private final BookClient bookClient;

    @PostMapping("/create")
    public ResponseEntity<?> createRent(@RequestParam Long readerId, @RequestParam Long bookId) {

            ResponseEntity<?> readerResponse = readerClient.getReaderById(readerId);
            if (readerResponse.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reader not found!");
            }

            ResponseEntity<?> bookResponse = bookClient.getBookById(bookId);
            if (bookResponse.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
            }

            ResponseEntity<?> countDownResponse = bookClient.decreaseBookCount(bookId);
            if (countDownResponse.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Zero book count!");
            }

        return ResponseEntity.ok(rentService.createNewRent(readerId, bookId));
    }
}
