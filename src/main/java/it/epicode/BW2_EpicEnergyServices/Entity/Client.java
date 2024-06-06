package it.epicode.BW2_EpicEnergyServices.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.epicode.BW2_EpicEnergyServices.Enums.ClientType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue
    private int clientId;
    private String societyName;
    @Enumerated(EnumType.STRING)
    private ClientType clientType;
    private String vat;
    private String email;
    private LocalDate addDate;
    private LocalDate lastContact;
    private double totalSales;
    private String pec;
    private long phoneNumber;
    private String contactEmail;
    private String contactName;
    private String contactSurname;
    private long contactPhone;
    private String societyLogo;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Address> address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Turnover> turnoverList;




}
