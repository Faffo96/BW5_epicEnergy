package it.epicode.BW2_EpicEnergyServices.Service;

import it.epicode.BW2_EpicEnergyServices.Dto.UserLoginDto;

import it.epicode.BW2_EpicEnergyServices.Entity.User;
import it.epicode.BW2_EpicEnergyServices.Exceptions.UnauthorizedException;
import it.epicode.BW2_EpicEnergyServices.Security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUserAndCreateToken(UserLoginDto userLoginDto) {
        User user = userService.getUserByEmail(userLoginDto.getEmail()); //METODO DA INSERIRE

        if (passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            return jwtTool.createToken(user);

        } else {
            throw new UnauthorizedException("Error in authorization, relogin!");
        }
    }
}
