package it.epicode.BW2_EpicEnergyServices.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank
    @Size(min = 4, max = 15)
    private String username;
    @NotBlank
    @Email
    @Size(min = 2, max = 20)
    private String email;
    @NotBlank
    @Size(min = 3, max = 15)
    private String password;
    @NotBlank
    @Size(min = 2, max = 30)
    private String name;
    @NotBlank
    @Size(min = 1, max = 15)
    private String surname;
    private String avatar;
}
