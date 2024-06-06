package it.epicode.BW2_EpicEnergyServices.Dto;

import it.epicode.BW2_EpicEnergyServices.Entity.Address;
import it.epicode.BW2_EpicEnergyServices.Enums.ClientType;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ClientDto {
    @NotBlank
    @Size(min = 4, max = 15)
    private String societyName;

    private ClientType clientType;
    @NotBlank
    @Size(min = 11, max = 11)
    private String vat;
    @NotBlank
    @Email
    @Size(min = 2, max = 50)
    private String email;
    @NotNull
    private LocalDate addDate;
    @NotNull
    private LocalDate lastContact;
    @NotNull
    @Positive
    private double totalSales;
    @NotBlank
    @Email
    @Size(min = 2, max = 50)
    private String pec;
    @NotNull
    @Positive
    private long phoneNumber;
    @NotBlank
    @Email
    @Size(min = 2, max = 50)
    private String contactEmail;
    @NotBlank
    @Size(min = 1, max = 50)
    private String contactName;
    @NotBlank
    @Size(min = 1, max = 50)
    private String contactSurname;


    private long contactPhone;

    private String societyLogo;


    private List<Address> address;


}
