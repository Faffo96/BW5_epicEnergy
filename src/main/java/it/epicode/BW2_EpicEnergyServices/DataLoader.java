package it.epicode.BW2_EpicEnergyServices;

import com.opencsv.exceptions.CsvException;
import it.epicode.BW2_EpicEnergyServices.Entity.Client;
import it.epicode.BW2_EpicEnergyServices.Entity.Turnover;
import it.epicode.BW2_EpicEnergyServices.Enums.TurnoverState;
import it.epicode.BW2_EpicEnergyServices.Repository.ClientRepository;
import it.epicode.BW2_EpicEnergyServices.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TownService townService;

    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TurnoverService turnoverService;

    @Override
    public void run(String... args) throws Exception {
      /* try {
           provinceService.importProvincesFromCSV("province-italiane.csv");
       } catch (IOException | CsvException e) {
           e.printStackTrace();
       }

       try {
           townService.importTownsFromCSV("comuni-italiani.csv");
       } catch (IOException | CsvException e) {
           e.printStackTrace();
       }*/

        /*System.out.println(clientService.orderClientsBySocietyName());*/
        /*System.out.println(clientService.orderClientsByTotalSales());*/
        /*System.out.println(clientService.orderClientsByDate());*/
        /*System.out.println(clientService.orderClientsByLastContact());*/

       /* Page<Client> clientPage1 = clientService.filterClientsBySocietyNameContaining("Tunia", 0);
        List<Client> clients = clientPage1.getContent();
        for (Client client : clients) {
            System.out.println(client);
        }*/

        /*Page<Client> clientPage2 = clientService.filterClientsByTotalSalesGreaterThan(20, 0);
        List<Client> clients2 = clientPage2.getContent();
        for (Client client : clients2) {
            System.out.println(client);
        }*/

        /*Page<Client> clientPage3 = clientService.filterClientsByAddDateAfter(LocalDate.of(2023, 01, 01), 0);
        List<Client> clients3 = clientPage3.getContent();
        for (Client client : clients3) {
            System.out.println(client);
        }*/

        /*Page<Client> clientPage4 = clientService.filterClientsByLastContactAfter(LocalDate.of(2023,05,15), 0);
        List<Client> clients4 = clientPage4.getContent();
        for (Client client : clients4) {
            System.out.println(client);
        }*/

        /*Page<Turnover> turnoverPage1 = turnoverService.filterTurnoversByTurnoverState(TurnoverState.PAYED, 0);
        List<Turnover> turnovers = turnoverPage1.getContent();
        for (Turnover turnover : turnovers) {
            System.out.println(turnover);
        }*/

        /*Page<Turnover> turnoverPage2 = turnoverService.filterTurnoversByDate(LocalDate.of(2023, 06, 01), 0);
        List<Turnover> turnovers = turnoverPage2.getContent();
        for (Turnover turnover : turnovers) {
            System.out.println(turnover);
        }*/

        Page<Turnover> turnoverPage3 = turnoverService.findTurnoverBySocietyName("Tunia", 0, 15);
        List<Turnover> turnovers = turnoverPage3.getContent();
        for (Turnover turnover : turnovers) {
            System.out.println(turnover);
        }

        /*Client client = clientRepository.findBySocietyName("tunia");
        System.out.println("Client found manually: " + client);*/


        /*Page<Turnover> turnoverPage3 = turnoverService.filterTurnoversByYear(2023, 0);
        List<Turnover> turnovers = turnoverPage3.getContent();
        for (Turnover turnover : turnovers) {
            System.out.println(turnover);
        }*/

        /*Page<Turnover> turnoverPage3 = turnoverService.filterTurnoversByTotalImportRange(1600, 1700, 0);
        List<Turnover> turnovers = turnoverPage3.getContent();
        for (Turnover turnover : turnovers) {
            System.out.println(turnover);
        }*/

//        /*System.out.println(provinceService.getProvinceByName("Torino"));*/
    }
}


