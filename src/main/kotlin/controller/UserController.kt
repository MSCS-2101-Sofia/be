package org.tennismate.com.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.tennismate.com.service.MatchingService
import java.sql.SQLException
import javax.servlet.http.HttpServletRequest

@RestController
class UserController @Autowired constructor(
    //private val userRepository: UserRepository,
    matchingService: MatchingService
) {
    //TODO: login use JWT
    @PostMapping("/api/register")
    fun postRegister(request: String) {

    }

    @PostMapping("/api/login")
    fun postLogin(request: HttpServletRequest): ResponseEntity<String> {
        try {
            val username = request.getParameter("username")
            val pwd = request.getParameter("password_md5")
            //val user = userRepository.findByName(username)
            //if(user.size == 1 && user[0].passwordMD5 == pwd) {
            //return ResponseEntity.status(HttpStatus.OK).body("successfully get user info")
            //}
        } catch (sqlException: SQLException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error retrieving user information from user database")
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no user found")
    }
}