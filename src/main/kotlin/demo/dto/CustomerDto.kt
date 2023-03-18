package demo.dto

import java.time.LocalDateTime
import java.util.UUID

data class CustomerDto(
    val id: UUID?,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val app: String,
    val date: LocalDateTime = LocalDateTime.now()
)
