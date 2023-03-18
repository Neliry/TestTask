package demo.utils

import com.google.i18n.phonenumbers.PhoneNumberUtil

object CustomerInfoFormatter {
    const val UNKNOWN_REGION = "ZZ"

    fun formatEmail(email: String) = email.trim().lowercase()

    fun formatPhoneNumber(phoneNumber: String): String {
        val phoneNumberUtil = PhoneNumberUtil.getInstance()
        val parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, UNKNOWN_REGION)
        return phoneNumberUtil.format(parsedPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
    }
}
