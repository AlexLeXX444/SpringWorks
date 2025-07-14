package ru.app.rentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.app.rentservice.entity.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
}
