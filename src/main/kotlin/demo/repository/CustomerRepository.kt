package demo.repository

import demo.entity.CustomerEntity
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerEntity, String> {
    fun findByEmail(email: String): CustomerEntity?

    fun findByPhoneNumber(phoneNumber: String): CustomerEntity?

    fun findFirstByEmailOrPhoneNumber(email: String?, phoneNumber: String?): CustomerEntity?
}