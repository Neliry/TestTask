package demo.repository

import demo.entity.CustomerEntity
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerEntity, String> {
    fun findFirstByEmailOrPhoneNumber(email: String?, phoneNumber: String?): CustomerEntity?
}