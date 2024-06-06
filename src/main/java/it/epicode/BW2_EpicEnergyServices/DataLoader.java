package it.epicode.BW2_EpicEnergyServices;

import com.opencsv.exceptions.CsvException;
import it.epicode.BW2_EpicEnergyServices.Entity.Client;
import it.epicode.BW2_EpicEnergyServices.Service.ClientService;
import it.epicode.BW2_EpicEnergyServices.Service.ProvinceService;
import it.epicode.BW2_EpicEnergyServices.Service.TownService;
import it.epicode.BW2_EpicEnergyServices.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TownService townService;

    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;
    @Override
    public void run(String... args) throws Exception {
//        try {
//            provinceService.importProvincesFromCSV("province-italiane.csv");
//        } catch (IOException | CsvException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            townService.importTownsFromCSV("comuni-italiani.csv");
//        } catch (IOException | CsvException e) {
//            e.printStackTrace();
//        }
//
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

//        /*System.out.println(provinceService.getProvinceByName("Torino"));*/
    }
}


