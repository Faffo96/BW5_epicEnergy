package it.epicode.BW2_EpicEnergyServices.Exceptions;

public class ClientNotFoundException extends RuntimeException  {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
