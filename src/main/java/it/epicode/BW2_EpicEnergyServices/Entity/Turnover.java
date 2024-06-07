package it.epicode.BW2_EpicEnergyServices.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.BW2_EpicEnergyServices.Enums.TurnoverState;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Turnover {

    @Id
    @GeneratedValue
    private int id;
    private Long turnoverCode;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TurnoverState turnoverState;

    private double total;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    @Override
    public String toString() {
        return "Turnover{" +
                "id=" + id +
                ", turnoverCode=" + turnoverCode +
                ", date=" + date +
                ", turnoverState=" + turnoverState +
                ", total=" + total +
                '}';
    }
}
