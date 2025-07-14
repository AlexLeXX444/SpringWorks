package ru.app.rentservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.rentservice.entity.Rent;
import ru.app.rentservice.repository.RentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentService {

    private final RentRepository rentRepository;

    public Rent createNewRent(long readerId, long bookId) {
        return rentRepository.save(new Rent(readerId, bookId));
    }
}
