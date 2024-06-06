package it.epicode.BW2_EpicEnergyServices.Repository;

import it.epicode.BW2_EpicEnergyServices.Entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByEmail(String email);

    @Query("SELECT c FROM Client c WHERE LOWER(c.societyName) = LOWER(:societyName)")
    Client findBySocietyName(@Param("societyName") String societyName);

    Page<Client> findByTotalSalesGreaterThan(double totalSales, Pageable pageable);
    Page<Client> findByAddDateAfter(LocalDate addDate, Pageable pageable);
    Page<Client> findByLastContactAfter(LocalDate lastContact, Pageable pageable);
    Page<Client> findBySocietyNameContainingIgnoreCase(String societyNamePart, Pageable pageable);
}
