package it.epicode.BW2_EpicEnergyServices.Service;

import it.epicode.BW2_EpicEnergyServices.Dto.TurnoverDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Turnover;
import it.epicode.BW2_EpicEnergyServices.Enums.TurnoverState;
import it.epicode.BW2_EpicEnergyServices.Exceptions.TurnoverNotFoundException;
import it.epicode.BW2_EpicEnergyServices.Repository.TurnoverRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoverService {

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
}

