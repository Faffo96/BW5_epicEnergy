package it.epicode.BW2_EpicEnergyServices.Exceptions;

public class TownNotFoundException extends RuntimeException {
    public TownNotFoundException(String message) {
        super(message);
    }
}

