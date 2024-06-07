package org.tennismate.com.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.tennismate.com.common.data.User
import org.tennismate.com.common.data.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {
    private val passwordEncoder = BCryptPasswordEncoder()

    fun registerUser(user: User): User {
        val encodedPassword = passwordEncoder.encode(user.password)
        val newUser = user.copy(password = encodedPassword)
        return userRepository.save(newUser)
    }

    fun authenticateUser(username: String, password: String): User? {
        val user = userRepository.findByUsername(username) ?: return null
        return if (passwordEncoder.matches(password, user.password)) user else null
    }
}
