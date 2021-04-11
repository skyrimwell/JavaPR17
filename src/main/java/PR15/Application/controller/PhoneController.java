package PR15.Application.controller;

import PR15.Application.service.PhoneService;
import PR15.Application.model.Phone;


import PR15.Application.model.Manufacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @PostMapping("/phone")
    public void addPhone(@RequestBody Phone phone) {
        phoneService.addPhone(phone);
    }

    @GetMapping("/phones")
    public List<Phone> getAll() {
        return phoneService.getPhone();
    }

    @GetMapping("/getPFilteredByName")
    public List<Phone> getFilteredByName(){
        return phoneService.getByName();
    }

    @GetMapping("/getPFilteredByYear")
    public List<Phone> getFilteredByYear(){
        return phoneService.getByYear();
    }

    @DeleteMapping("/phone/{id}")
    public void deletePhone(@PathVariable UUID id) {
        phoneService.deletePhone(id);
    }

    @GetMapping(value = "/level/{Id}/manufacture")
    public @ResponseBody
    Manufacture getManufacture(@PathVariable("Id") UUID id){
        return phoneService.getManufacture(id);
    }


}
