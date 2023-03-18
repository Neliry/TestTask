package demo.controller

import demo.dto.CustomerCreateDto
import demo.dto.CustomerDto
import demo.meta.Endpoints.CUSTOMERS
import demo.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(CUSTOMERS)
@Tag(name = "Customer Controller")
class CustomerController(
    private val customerService: CustomerService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save customer")
    fun save(@Valid @RequestBody customer: CustomerCreateDto): CustomerDto {
        return customerService.save(customer)
    }

    @GetMapping("/by-email")
    @Operation(summary = "Get customer by email")
    fun getByEmail(@Parameter(description = "Email of customer to be searched") @RequestParam email: String): CustomerDto {
        return customerService.getByEmail(email)
    }

    @GetMapping("/by-phone-number")
    @Operation(summary = "Get customer by phone number")
    fun getByPhoneNumber(
        @Parameter(description = "Phone number of customer to be searched") @RequestParam phoneNumber: String
    ): CustomerDto {
        return customerService.getByPhoneNumber(phoneNumber)
    }
}
