package org.tennismate.com.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUserDetailsService : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        // Replace with your logic to load the user from the database
        return User(username, "password", ArrayList())
    }
}