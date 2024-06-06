package it.epicode.BW2_EpicEnergyServices.Repository;

import it.epicode.BW2_EpicEnergyServices.Entity.Client;
import it.epicode.BW2_EpicEnergyServices.Entity.Turnover;
import it.epicode.BW2_EpicEnergyServices.Enums.TurnoverState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TurnoverRepository extends JpaRepository<Turnover, Integer> {

    /*Page<Turnover> findByClientSocietyName(@Param("societyName") String societyName, Pageable pageable);*/

    Page<Turnover> findByClient(Client client, Pageable pageable);

    Page<Turnover> findByTurnoverState(TurnoverState turnoverState, Pageable pageable);

    Page<Turnover> findByDate(LocalDate date, Pageable pageable);

    Page<Turnover> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<Turnover> findByTotalBetween(double minTotal, double maxTotal, Pageable pageable);
}
