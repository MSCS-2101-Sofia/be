package org.tennismate.com.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.tennismate.com.common.data.ListOfUsersResponse
import org.tennismate.com.common.data.UserRepository
import javax.servlet.http.HttpServletRequest

@RestController
class MatchingController @Autowired constructor(
    val userRepository: UserRepository
) {
    @GetMapping("/ping")
    fun getTest(): String {
        return "ping"
    }

    //TODO: currently this returns all registered users,
    @GetMapping("/api/users")
    fun getUsers(): ResponseEntity<String> {
        val recommendedUsers = userRepository.findAll()
        val responsePOJO = ListOfUsersResponse.fromListOfUser(recommendedUsers)
        return ResponseEntity.status(HttpStatus.OK).body(ObjectMapper().writeValueAsString(responsePOJO))
    }

    @PatchMapping("/api/match-request/{id}")
    fun patchMatchRequest(
        @PathVariable(value = "id", required = true) userId: String,
        request: HttpServletRequest,
    ): ResponseEntity<String> {
        //TODO: request parsing using ObjectMapper
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }
}