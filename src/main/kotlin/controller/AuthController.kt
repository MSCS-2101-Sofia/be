package org.tennismate.com.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.tennismate.com.common.data.User
import org.tennismate.com.service.JwtService
import org.tennismate.com.service.UserService

@RestController
@RequestMapping("/api/auth")
class AuthController(private val userService: UserService, private val jwtService: JwtService) {

    data class AuthRequest(val username: String, val password: String)
    data class AuthResponse(val token: String)

    @PostMapping("/signup")
    fun signup(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.ok(userService.registerUser(user))
    }

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> {
        val user = userService.authenticateUser(authRequest.username, authRequest.password)
            ?: return ResponseEntity.status(401).build()

        val token = jwtService.generateToken(authRequest.username)
        return ResponseEntity.ok(AuthResponse(token))
    }
}
