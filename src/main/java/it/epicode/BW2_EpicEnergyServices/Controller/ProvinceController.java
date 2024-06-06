package it.epicode.BW2_EpicEnergyServices.Controller;

import it.epicode.BW2_EpicEnergyServices.Dto.ProvinceDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Province;
import it.epicode.BW2_EpicEnergyServices.Exceptions.BadRequestException;
import it.epicode.BW2_EpicEnergyServices.Service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @PostMapping("/provinces")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveProvince(@RequestBody @Validated ProvinceDto province, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return provinceService.saveProvince(province);
    }

    @GetMapping("/provinces")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Page<Province> getAllProvinces(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "provinceId") String sortBy) {
        return provinceService.getAllProvince(page, size, sortBy);
    }

    @GetMapping("/provinces/{provinceId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Province getProvinceById(@PathVariable int provinceId) {
        return provinceService.getProvinceById(provinceId);
    }

    @PutMapping(path = "/provinces/{provinceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public @ResponseBody String updateProvince(@PathVariable int provinceId, @RequestBody @Validated ProvinceDto province, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return provinceService.updateProvince(provinceId, province);
    }

    @DeleteMapping("/provinces/{provinceId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProvince(@PathVariable int provinceId) {
        return provinceService.deleteProvince(provinceId);
    }

}