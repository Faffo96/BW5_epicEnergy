package it.epicode.BW2_EpicEnergyServices.Dto;

import it.epicode.BW2_EpicEnergyServices.Entity.Province;
import lombok.Data;

@Data
public class TownDto {
    private Province province;
    private String townName;

}
