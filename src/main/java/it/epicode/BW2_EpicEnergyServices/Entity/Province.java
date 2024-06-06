package it.epicode.BW2_EpicEnergyServices.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Province {

    @Id
    @GeneratedValue
    private int provinceId;

    @OneToMany(mappedBy="province")
    @JsonIgnore
    private List<Town> town;

    private String acronym;

    private String name;

    @Override
    public String toString() {
        return "Province{" +
                "provinceId=" + provinceId +
                ", acronym='" + acronym + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}


