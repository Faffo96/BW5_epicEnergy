package it.epicode.BW2_EpicEnergyServices.Service;

import it.epicode.BW2_EpicEnergyServices.Dto.ClientDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Client;
import it.epicode.BW2_EpicEnergyServices.Entity.User;
import it.epicode.BW2_EpicEnergyServices.Exceptions.ClientNotFoundException;
import it.epicode.BW2_EpicEnergyServices.Exceptions.UserNotFoundException;
import it.epicode.BW2_EpicEnergyServices.Repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public String saveClient(ClientDto clientDto) {
        Client client = new Client();
        client.setSocietyName(clientDto.getSocietyName());
        client.setClientType(clientDto.getClientType());
        client.setVat(clientDto.getVat());
        client.setEmail(clientDto.getEmail());
        client.setAddDate(clientDto.getAddDate());
        client.setLastContact(clientDto.getLastContact());
        client.setTotalSales(clientDto.getTotalSales());
        client.setPec(clientDto.getPec());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setContactEmail(clientDto.getContactEmail());
        client.setContactName(clientDto.getContactName());
        client.setContactSurname(clientDto.getContactSurname());
        client.setContactPhone(clientDto.getContactPhone());
        client.setSocietyLogo(clientDto.getSocietyLogo());
        client.setAddress(clientDto.getAddress());

        clientRepository.save(client);

        return "Client with id " + client.getClientId() + " correctly saved!";
    }

    public Page<Client> getAllClients(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return clientRepository.findAll(pageable);
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id " + id));
    }

    public String updateClient(int id, ClientDto clientDto) {
        Client client = getClientById(id);
        client.setSocietyName(clientDto.getSocietyName());
        client.setClientType(clientDto.getClientType());
        client.setVat(clientDto.getVat());
        client.setEmail(clientDto.getEmail());
        client.setAddDate(clientDto.getAddDate());
        client.setLastContact(clientDto.getLastContact());
        client.setTotalSales(clientDto.getTotalSales());
        client.setPec(clientDto.getPec());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setContactEmail(clientDto.getContactEmail());
        client.setContactName(clientDto.getContactName());
        client.setContactSurname(clientDto.getContactSurname());
        client.setContactPhone(clientDto.getContactPhone());
        client.setSocietyLogo(clientDto.getSocietyLogo());
        client.setAddress(clientDto.getAddress());

        clientRepository.save(client);

        return "Client with id " + client.getClientId() + " correctly updated!";
    }

    public String deleteClient(int id) {
        Client client = getClientById(id);
        clientRepository.deleteById(id);
        return "Client with id=" + id + " correctly deleted!";
    }

    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email " + email));
    }

    public List<Client> orderClientsBySocietyName() {
        return clientRepository.findAll(Sort.by(Sort.Direction.ASC, "societyName"));
    }

    public List<Client> orderClientsByTotalSales() {
        return clientRepository.findAll(Sort.by(Sort.Direction.DESC, "totalSales"));
    }

    public List<Client> orderClientsByDate() {
        return clientRepository.findAll(Sort.by(Sort.Direction.DESC, "addDate"));
    }

    public List<Client> orderClientsByLastContact() {
        return clientRepository.findAll(Sort.by(Sort.Direction.DESC, "lastContact"));
    }

    public Page<Client> filterClientsByTotalSalesGreaterThan(double totalSales, int page) {
        int size = 15; // Valore predefinito
        Pageable pageable = PageRequest.of(page, size, Sort.by("totalSales").descending());
        return clientRepository.findByTotalSalesGreaterThan(totalSales, pageable);
    }

    public Page<Client> filterClientsByAddDateAfter(LocalDate addDate, int page) {

            int size = 15; // Valore predefinito

        Pageable pageable = PageRequest.of(page, size, Sort.by("addDate").descending());
        return clientRepository.findByAddDateAfter(addDate, pageable);
    }

    public Page<Client> filterClientsByLastContactAfter(LocalDate lastContact, int page) {
        int size = 15; // Valore predefinito
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastContact").descending());
        return clientRepository.findByLastContactAfter(lastContact, pageable);
    }

    public Page<Client> filterClientsBySocietyNameContaining(String societyNamePart, int page) {
        int size = 15; // Valore predefinito
        Pageable pageable = PageRequest.of(page, size, Sort.by("societyName"));
        return clientRepository.findBySocietyNameContainingIgnoreCase(societyNamePart, pageable);
    }
}
