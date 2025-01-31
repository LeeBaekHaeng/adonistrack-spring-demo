package com.woozooha.demo.controller;

import com.woozooha.demo.dto.CustomerDto;
import com.woozooha.demo.service.CustomerService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer/{id}")
    public CustomerDto get(@Parameter(example = "1") @PathVariable Long id) {
        return customerService.get(id);
    }

    @PostMapping("/customer")
    public CustomerDto create(@RequestBody CustomerDto dto) {
        return customerService.create(dto);
    }

    @PutMapping("/customer/{id}")
    public void update(@Parameter(example = "1") @PathVariable Long id, @RequestBody CustomerDto dto) {
        dto.setId(id);

        customerService.update(dto);
    }

    @DeleteMapping("/customer/{id}")
    public void delete(@Parameter(example = "1") @PathVariable Long id) {
        customerService.delete(id);
    }

}
