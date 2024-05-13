package org.tennismate.com.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class MatchingController {
    @GetMapping("/ping")
    fun getTest(): String {
        return "ping"
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