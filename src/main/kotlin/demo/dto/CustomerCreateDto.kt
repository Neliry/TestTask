package demo.dto

import demo.meta.ErrorMessages.EMAIL_IS_BLANK
import demo.meta.ErrorMessages.EMAIL_IS_INVALID
import demo.meta.ErrorMessages.PHONE_NUMBER_IS_BLANK
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class CustomerCreateDto(
    val firstName: String,
    val lastName: String,
    @field:NotBlank(message = PHONE_NUMBER_IS_BLANK)
    val phoneNumber: String,
    @field:NotBlank(message = EMAIL_IS_BLANK)
    @field:Email(message = EMAIL_IS_INVALID)
    val email: String,
    val app: String,
    val date: LocalDateTime = LocalDateTime.now()
)
