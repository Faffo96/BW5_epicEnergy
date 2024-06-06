package it.epicode.BW2_EpicEnergyServices.Service;

import it.epicode.BW2_EpicEnergyServices.Dto.AddressDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Address;
import it.epicode.BW2_EpicEnergyServices.Exceptions.AddressNotFoundException;
import it.epicode.BW2_EpicEnergyServices.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public String saveAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());
        address.setCap(addressDto.getCap());
        address.setClient(addressDto.getClient());
        address.setTown(addressDto.getTown());

        addressRepository.save(address);

        return "Address with id " + address.getAddressId() + " correctly saved!";
    }

    public Page<Address> getAllAddresses(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return addressRepository.findAll(pageable);
    }

    public Address getAddressById(int id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address not found with id " + id));
    }

    public String updateAddress(int id, AddressDto addressDto) {
        Address address = getAddressById(id);
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());
        address.setCap(addressDto.getCap());
        address.setClient(addressDto.getClient());
        address.setTown(addressDto.getTown());

        addressRepository.save(address);

        return "Address with id " + address.getAddressId() + " correctly updated!";
    }

    public String deleteAddress(int id) {
        Address address = getAddressById(id);
        addressRepository.deleteById(id);
        return "Address with id=" + id + " correctly deleted!";
    }


}
