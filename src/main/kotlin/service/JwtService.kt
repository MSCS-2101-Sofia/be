package org.tennismate.com.service

import org.springframework.stereotype.Service
import org.tennismate.com.util.JwtUtil

@Service
class JwtService(private val jwtUtil: JwtUtil) {
    fun generateToken(username: String): String {
        return jwtUtil.generateToken(username)
    }

    fun validateToken(token: String): Boolean {
        return jwtUtil.validateToken(token)
    }

    fun extractUsername(token: String): String {
        return jwtUtil.extractUsername(token)
    }
}
