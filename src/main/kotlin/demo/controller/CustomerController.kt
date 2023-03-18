package demo.controller

import demo.dto.CustomerCreateDto
import demo.dto.CustomerDto
import demo.meta.Endpoints.CUSTOMERS
import demo.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(CUSTOMERS)
class CustomerController(
    private val customerService: CustomerService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@Valid @RequestBody customer: CustomerCreateDto): CustomerDto {
        return customerService.save(customer)
    }
}
