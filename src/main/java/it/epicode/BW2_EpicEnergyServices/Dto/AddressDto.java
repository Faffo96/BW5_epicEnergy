package it.epicode.BW2_EpicEnergyServices.Dto;

import it.epicode.BW2_EpicEnergyServices.Entity.Address;
import it.epicode.BW2_EpicEnergyServices.Entity.Client;
import it.epicode.BW2_EpicEnergyServices.Entity.Town;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class AddressDto {

    @NotBlank
    @Size(min = 5, max = 60)
    private String street;

    @NotBlank
    @Pattern(regexp = "\\d+", message = "Il campo 'number' deve contenere solo numeri.")
    @Size(min = 1, max = 10, message = "Il campo 'number' deve contenere tra 1 e 10 numeri.")
    private String number;
    @NotBlank
    @Min(5) @Max(5)
    private int cap;

    private Client client;
    @NotNull
    private Town town;
}
