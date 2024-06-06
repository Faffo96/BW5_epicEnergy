package it.epicode.BW2_EpicEnergyServices.Exceptions;

public class TurnoverNotFoundException extends RuntimeException {
    public TurnoverNotFoundException(String message) {
        super(message);
    }
}
