package org.tennismate.com.Controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MatchingController {
    @GetMapping("/ping")
    fun getTest(): String {
        return "ping"
    }

    @GetMapping("/signin")
    fun getSignIn(): String {
        return "sorry no signin func for now"
    }
}