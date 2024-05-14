package org.tennismate.com.common.data

import org.springframework.beans.factory.annotation.Autowired

data class RegisterRequest (
    val username: String,
    val password: String,
    val gender: String?,
    val tennisLevel: String?,
    val zipCode: String?,
    val phoneNumber: String?
) {
    companion object {
        fun toNewUser(registerRequest: RegisterRequest): User {
            return User(
                registerRequest.username,
                registerRequest.password,
                gender = registerRequest.gender,
                tennisLevel = registerRequest.tennisLevel,
                phoneNumber = registerRequest.phoneNumber,
                zipCode = registerRequest.zipCode
            )
        }
    }
}
