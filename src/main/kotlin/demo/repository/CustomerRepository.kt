package demo.repository

import demo.entity.CustomerEntity
import java.util.UUID
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerEntity, UUID> {
    fun findByEmail(email: String): CustomerEntity?

    fun findByPhoneNumber(phoneNumber: String): CustomerEntity?

    fun findFirstByEmailOrPhoneNumber(email: String?, phoneNumber: String?): CustomerEntity?
}
