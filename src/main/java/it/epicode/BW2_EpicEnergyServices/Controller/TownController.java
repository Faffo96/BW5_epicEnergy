package it.epicode.BW2_EpicEnergyServices.Controller;

import it.epicode.BW2_EpicEnergyServices.Dto.TownDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Town;
import it.epicode.BW2_EpicEnergyServices.Exceptions.BadRequestException;
import it.epicode.BW2_EpicEnergyServices.Service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class TownController {
    @Autowired
    private TownService townService;


    @PostMapping("/towns")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveTown(@RequestBody @Validated TownDto town, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return townService.saveTown(town);
    }

    @GetMapping("/towns")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Page<Town> getAllTown(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "townId") String sortBy) {
        return townService.getAllTown(page, size, sortBy);
    }

    @GetMapping("/towns/{townId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Town getTownById(@PathVariable int townId) {
        return townService.getTownById(townId);
    }

    @PutMapping(path = "/towns/{townId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public @ResponseBody Town updateTown(@PathVariable int townId, @RequestBody @Validated TownDto town, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return townService.updateTown(townId, town);
    }

    @DeleteMapping("/towns/{townId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteTown(@PathVariable int townId) {
        return townService.deleteTown(townId);
    }

}

