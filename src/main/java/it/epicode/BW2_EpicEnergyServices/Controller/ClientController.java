package it.epicode.BW2_EpicEnergyServices.Controller;

import it.epicode.BW2_EpicEnergyServices.Dto.ClientDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Client;
import it.epicode.BW2_EpicEnergyServices.Exceptions.BadRequestException;
import it.epicode.BW2_EpicEnergyServices.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveClient(@RequestBody @Validated ClientDto client, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (s, s2) -> s + s2));
        }
        return clientService.saveClient(client);
    }

    @GetMapping("/clients")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Page<Client> getAllClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "clientId") String sortBy) {
        return clientService.getAllClients(page, size, sortBy);
    }

    @GetMapping("/clients/{clientId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Client getClientById(@PathVariable int clientId) {
        return clientService.getClientById(clientId);
    }

    @PutMapping(path = "/clients/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public @ResponseBody String updateClient(@PathVariable int clientId, @RequestBody @Validated ClientDto client, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (s, s2) -> s + s2));
        }
        return clientService.updateClient(clientId, client);
    }

    @DeleteMapping("/clients/{clientId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteClient(@PathVariable int clientId) {
        return clientService.deleteClient(clientId);
    }

    @GetMapping("/clients/bySocietyName")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Client> orderClientsBySocietyName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {
        return clientService.orderClientsBySocietyName(page, size);
    }

    @GetMapping("/clients/byTotalSales")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Client> orderClientsByTotalSales(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {
        return clientService.orderClientsByTotalSales(page, size);
    }

    @GetMapping("/clients/byDate")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Client> orderClientsByDate(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {
        return clientService.orderClientsByDate(page, size);
    }

    @GetMapping("/clients/byLastContact")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Client> orderClientsByLastContact(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {
        return clientService.orderClientsByLastContact(page, size);
    }


    @GetMapping("/clients/BySocietyNameContaining")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Client> filterClientsBySocietyNameContaining(String societyNamePart, Integer page) {
        int pageNumber = (page != null) ? page : 0;

        Page<Client> clientPage = clientService.filterClientsBySocietyNameContaining(societyNamePart, pageNumber);
        List<Client> clients = clientPage.getContent();
        return clients;
    }

    @GetMapping("/clients/ByTotalSalesGreaterThan")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Client> filterClientsByTotalSalesGreaterThan(@RequestParam("salesAmount") double salesAmount, @RequestParam(value = "page", required = false) Integer page) {
        int pageNumber = (page != null) ? page : 0;

        Page<Client> clientPage = clientService.filterClientsByTotalSalesGreaterThan(salesAmount, pageNumber);
        List<Client> clients = clientPage.getContent();
        return clients;
    }


    @GetMapping("/clients/ByAddDateAfter")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Client> filterClientsByAddDateAfter(@RequestParam("addDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate addDate, @RequestParam(value = "page", required = false) Integer page) {
        int pageNumber = (page != null) ? page : 0;

        Page<Client> clientPage = clientService.filterClientsByAddDateAfter(addDate, pageNumber);
        return clientPage;
    }


    @GetMapping("/clients/ByLastContactAfter")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Client> filterClientsByLastContactAfter(@RequestParam("lastContactDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastContactDate, @RequestParam(value = "page", required = false) Integer page) {
        int pageNumber = (page != null) ? page : 0;

        Page<Client> clientPage = clientService.filterClientsByLastContactAfter(lastContactDate, pageNumber);
        List<Client> clients = clientPage.getContent();
        return clients;
    }

}
