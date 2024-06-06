package it.epicode.BW2_EpicEnergyServices.Service;

import it.epicode.BW2_EpicEnergyServices.Dto.TurnoverDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Client;
import it.epicode.BW2_EpicEnergyServices.Entity.Turnover;
import it.epicode.BW2_EpicEnergyServices.Enums.TurnoverState;
import it.epicode.BW2_EpicEnergyServices.Exceptions.TurnoverNotFoundException;
import it.epicode.BW2_EpicEnergyServices.Repository.ClientRepository;
import it.epicode.BW2_EpicEnergyServices.Repository.TurnoverRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoverService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TurnoverRepository turnoverRepository;

    private Long turnoverCode;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TurnoverState turnoverState;

    private double total;

    public String saveTurnover(TurnoverDto turnoverDto) {
        Turnover turnover = new Turnover();
        turnover.setTurnoverCode(turnoverDto.getTurnoverCode());
        turnover.setDate(turnoverDto.getDate());
        turnover.setTurnoverState(turnoverDto.getTurnoverState());
        turnover.setTotal(turnoverDto.getTotal());

        turnoverRepository.save(turnover);

        return "Turnover with id " + turnover.getId() + " correctly saved!";
    }

    public Page<Turnover> getAllTurnovers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return turnoverRepository.findAll(pageable);
    }

    public Turnover getTurnoverById(int id) {
        return turnoverRepository.findById(id)
                .orElseThrow(() -> new TurnoverNotFoundException("Turnover not found with id " + id));
    }

    public String updateTurnover(int id, TurnoverDto turnoverDto) {
        Turnover turnover = getTurnoverById(id);
        turnover.setTurnoverCode(turnoverDto.getTurnoverCode());
        turnover.setDate(turnoverDto.getDate());
        turnover.setTurnoverState(turnoverDto.getTurnoverState());
        turnover.setTotal(turnoverDto.getTotal());

        turnoverRepository.save(turnover);

        return "Turnover with id " + turnover.getId() + " correctly updated!";
    }

    public String deleteTurnover(int id) {
        Turnover turnover = getTurnoverById(id);
        turnoverRepository.deleteById(id);
        return "Turnover with id=" + id + " correctly deleted!";
    }

    @Transactional
    public Page<Turnover> findTurnoverBySocietyName(String societyName, int page, int size) {
        societyName = societyName.trim().toLowerCase();
        System.out.println("Searching for societyName: " + societyName);

        Pageable pageable = PageRequest.of(page, size);
        Page<Client> clients = clientRepository.findBySocietyNameContainingIgnoreCase(societyName, pageable);

        if (clients.hasContent()) {
            Client client = clients.getContent().get(0); // Prendi il primo cliente trovato
            return turnoverRepository.findByClient(client, pageable);
        } else {
            return Page.empty();
        }
    }

    public Page<Turnover> filterTurnoversByTurnoverState(TurnoverState turnoverState, int page) {
        int size = 15; // Valore predefinito
        Pageable pageable = PageRequest.of(page, size, Sort.by("turnoverState"));
        return turnoverRepository.findByTurnoverState(turnoverState, pageable);
    }

    public Page<Turnover> filterTurnoversByDate(LocalDate date, int page) {
        int size = 15; // Valore predefinito
        Pageable pageable = PageRequest.of(page, size, Sort.by("date"));
        return turnoverRepository.findByDate(date, pageable);
    }

    public Page<Turnover> filterTurnoversByYear(int year, int page) {
        int size = 15; // Valore predefinito
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        Pageable pageable = PageRequest.of(page, size, Sort.by("date"));
        return turnoverRepository.findByDateBetween(startDate, endDate, pageable);
    }

    public Page<Turnover> filterTurnoversByTotalImportRange(double minTotal, double maxTotal, int page) {
        int size = 15; // Valore predefinito
        Pageable pageable = PageRequest.of(page, size, Sort.by("total"));
        return turnoverRepository.findByTotalBetween(minTotal, maxTotal, pageable);
    }
}

