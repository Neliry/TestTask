package demo.mapper

import demo.dto.CustomerCreateDto
import demo.dto.CustomerDto
import demo.entity.CustomerEntity
import demo.utils.CustomerInfoFormatter.formatEmail
import demo.utils.CustomerInfoFormatter.formatPhoneNumber

fun CustomerCreateDto.toEntity() = CustomerEntity(
    firstName = firstName,
    lastName = lastName,
    phoneNumber = formatPhoneNumber(phoneNumber),
    email = formatEmail(email),
    app = app,
    date = date
)

fun CustomerEntity.toDto() = CustomerDto(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phoneNumber = phoneNumber,
    email = email,
    app = app,
    date = date
)
