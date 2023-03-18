package demo.service

import com.google.i18n.phonenumbers.PhoneNumberUtil
import demo.dto.CustomerCreateDto
import demo.dto.CustomerDto
import demo.entity.CustomerEntity
import demo.exeption.ConflictException
import demo.exeption.InvalidDataException
import demo.exeption.NotFoundException
import demo.mapper.toDto
import demo.mapper.toEntity
import demo.meta.ErrorMessages.CUSTOMER_ALREADY_EXISTS
import demo.meta.ErrorMessages.CUSTOMER_NOT_FOUND
import demo.meta.ErrorMessages.PHONE_NUMBER_IS_INVALID
import demo.repository.CustomerRepository
import demo.utils.CustomerInfoFormatter.UNKNOWN_REGION
import demo.utils.CustomerInfoFormatter.formatEmail
import demo.utils.CustomerInfoFormatter.formatPhoneNumber
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {
    @Transactional
    fun save(customerDto: CustomerCreateDto): CustomerDto {
        validateByPhoneNumber(customerDto.phoneNumber)
        var customer = customerDto.toEntity()
        validateByDuplicates(customer)
        customer = customerRepository.save(customer)
        return customer.toDto()
    }

    private fun validateByPhoneNumber(phoneNumber: String) {
        val phoneNumberUtil = PhoneNumberUtil.getInstance()
        val isPossibleNumber = phoneNumberUtil.isPossibleNumber(phoneNumber, UNKNOWN_REGION)
        if (!isPossibleNumber) throw InvalidDataException(PHONE_NUMBER_IS_INVALID)
    }

    private fun validateByDuplicates(newCustomer: CustomerEntity) {
        val existingCustomer = customerRepository.findFirstByEmailOrPhoneNumber(newCustomer.email, newCustomer.phoneNumber)
        if (existingCustomer != null) throw ConflictException(CUSTOMER_ALREADY_EXISTS)
    }

    fun getByEmail(email: String): CustomerDto {
        val formattedEmail = formatEmail(email)
        val customer = customerRepository.findByEmail(formattedEmail)
        return customer?.toDto() ?: throw NotFoundException(CUSTOMER_NOT_FOUND)
    }

    fun getByPhoneNumber(phoneNumber: String): CustomerDto {
        validateByPhoneNumber(phoneNumber)
        val formattedPhoneNumber = formatPhoneNumber(phoneNumber)
        val customer = customerRepository.findByPhoneNumber(formattedPhoneNumber)
        return customer?.toDto() ?: throw NotFoundException(CUSTOMER_NOT_FOUND)
    }
}
