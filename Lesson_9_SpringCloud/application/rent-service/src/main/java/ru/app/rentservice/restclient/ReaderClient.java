package ru.app.rentservice.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.app.rentservice.configuration.FeignConfiguration;

@FeignClient(name = "reader-service", configuration = FeignConfiguration.class)
public interface ReaderClient {

    @GetMapping("/reader/{id}")
    ResponseEntity<?> getReaderById(@PathVariable("id") Long id);
}
