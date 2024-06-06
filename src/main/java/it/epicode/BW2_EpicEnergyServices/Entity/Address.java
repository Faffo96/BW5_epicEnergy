package it.epicode.BW2_EpicEnergyServices.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue
    private int addressId;
    private String street;
    private String number;
    private int cap;
    @ManyToOne
    @JoinColumn(name="town_id")
    private Town town;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", cap=" + cap +
                ", town=" + town +
                ", client=" + client +
                "}";
    }
}
