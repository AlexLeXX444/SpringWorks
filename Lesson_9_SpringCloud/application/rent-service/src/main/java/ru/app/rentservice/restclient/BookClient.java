package ru.app.rentservice.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.app.rentservice.configuration.FeignConfiguration;

@FeignClient(name = "book-service", configuration = FeignConfiguration.class)
public interface BookClient {

    @GetMapping("/book/{id}")
    ResponseEntity<?> getBookById(@PathVariable("id") Long id);

    @PutMapping("/book/count-down/{id}")
    ResponseEntity<?> decreaseBookCount(@PathVariable("id") Long id);
}
