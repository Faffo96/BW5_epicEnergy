package it.epicode.BW2_EpicEnergyServices.Controller;

import it.epicode.BW2_EpicEnergyServices.Dto.AddressDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Address;
import it.epicode.BW2_EpicEnergyServices.Exceptions.BadRequestException;
import it.epicode.BW2_EpicEnergyServices.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/addresses")
    public Page<Address> getAllAddresses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "addressId") String sortBy) {
        return addressService.getAllAddresses(page, size, sortBy);
    }

    @GetMapping("/addresses/{addressId}")
    public Address getAddressById(@PathVariable int addressId) {
        return addressService.getAddressById(addressId);
    }

    @PostMapping("/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveAddress(@RequestBody @Validated AddressDto address, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (s, s2) -> s + s2));
        }
        return addressService.saveAddress(address);
    }

    @PutMapping(path = "/addresses/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String updateAddress(@PathVariable int addressId, @RequestBody @Validated AddressDto address, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (s, s2) -> s + s2));
        }
        return addressService.updateAddress(addressId, address);
    }

    @DeleteMapping("/addresses/{addressId}")
    public String deleteAddress(@PathVariable int addressId) {
        return addressService.deleteAddress(addressId);
    }
}

