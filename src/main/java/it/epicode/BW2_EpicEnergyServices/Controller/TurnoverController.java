package it.epicode.BW2_EpicEnergyServices.Controller;

import it.epicode.BW2_EpicEnergyServices.Dto.TurnoverDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Turnover;
import it.epicode.BW2_EpicEnergyServices.Enums.TurnoverState;
import it.epicode.BW2_EpicEnergyServices.Exceptions.BadRequestException;
import it.epicode.BW2_EpicEnergyServices.Service.TurnoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TurnoverController {
    @Autowired
    private TurnoverService turnoverService;


    @PostMapping("/turnovers")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveTurnover(@RequestBody @Validated TurnoverDto turnover, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (s, s2) -> s + s2));
        }
        return turnoverService.saveTurnover(turnover);
    }

    @GetMapping("/turnovers")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Page<Turnover> getAllTurnovers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "turnoverId") String sortBy) {
        return turnoverService.getAllTurnovers(page, size, sortBy);
    }

    @GetMapping("/turnovers/{turnoverId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Turnover getTurnoverById(@PathVariable int turnoverId) {
        return turnoverService.getTurnoverById(turnoverId);
    }

    @PutMapping(path = "/turnovers/{turnoverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public @ResponseBody String updateTurnover(@PathVariable int turnoverId, @RequestBody @Validated TurnoverDto turnover, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (s, s2) -> s + s2));
        }
        return turnoverService.updateTurnover(turnoverId, turnover);
    }

    @DeleteMapping("/turnovers/{turnoverId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteTurnover(@PathVariable int turnoverId) {
        return turnoverService.deleteTurnover(turnoverId);
    }

    @GetMapping("/turnovers/ByTurnoverState")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Turnover> filterTurnoversByTurnoverState(@RequestParam("state") TurnoverState state, @RequestParam(value = "page", required = false) Integer page) {
        int pageNumber = (page != null) ? page : 0;

        Page<Turnover> turnoverPage = turnoverService.filterTurnoversByTurnoverState(state, pageNumber);
        List<Turnover> turnovers = turnoverPage.getContent();
        return turnovers;
    }

    @GetMapping("/turnovers/ByDate")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Turnover> filterTurnoversByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam(value = "page", required = false) Integer page) {
        int pageNumber = (page != null) ? page : 0;

        Page<Turnover> turnoverPage = turnoverService.filterTurnoversByDate(date, pageNumber);
        List<Turnover> turnovers = turnoverPage.getContent();
        return turnovers;
    }

    @GetMapping("/turnover/byClientId")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Turnover> findTurnoverByClientId(@RequestParam int clientId, @RequestParam(required = false) Integer page) {
        int pageNumber = (page != null) ? page : 0;
        Page<Turnover> turnoverPage = turnoverService.findTurnoverByClientId(clientId, pageNumber, 15);
        return turnoverPage.getContent();
    }

    @GetMapping("/turnover/byYear")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Turnover> filterTurnoversByYear(@RequestParam int year, @RequestParam(required = false) Integer page) {
        int pageNumber = (page != null) ? page : 0;
        Page<Turnover> turnoverPage = turnoverService.filterTurnoversByYear(year, pageNumber);
        return turnoverPage.getContent();
    }

    @GetMapping("/turnover/byTotalImportRange")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Turnover> filterTurnoversByTotalImportRange(@RequestParam double minImport, @RequestParam double maxImport, @RequestParam(required = false) Integer page) {
        int pageNumber = (page != null) ? page : 0;
        Page<Turnover> turnoverPage = turnoverService.filterTurnoversByTotalImportRange(minImport, maxImport, pageNumber);
        return turnoverPage.getContent();
    }


}
