package org.tennismate.com.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.tennismate.com.common.data.MatchAction
import org.tennismate.com.common.data.MatchStatus
import org.tennismate.com.common.data.UserRepository
import org.tennismate.com.service.MatchingService

@RestController
@RequestMapping("/api/match")
class MatchingController @Autowired constructor(
    private val userRepository: UserRepository,
    private val matchingService: MatchingService
) {
    private var displayResults = listOf<MatchResponse>()
    data class MatchGetUserRequest(val username: String)
    data class MatchResponse(
        val id: Long?,
        val username: String,
        val city: String?,
        val tennisLevel: String?,
        val gender: String?,
        val phoneNumber: String?,
        var matchStatus: MatchStatus
    )
    data class MatchPatchRequest(val action: MatchAction)

    @GetMapping("/getMatch")
    fun getMatch(@RequestBody matchGetUserRequest: MatchGetUserRequest): ResponseEntity<List<MatchResponse>> {
        val currentUser = SecurityContextHolder.getContext().authentication?.principal
        val currentUsername = if (currentUser is UserDetails) currentUser.username else currentUser.toString()
        val user = userRepository.findByUsername(currentUsername)!!
        val response = matchingService.getMatch(user)
        displayResults = response
        return ResponseEntity.ok(response)
    }

    @PatchMapping("/updateMatch/{id}")
    fun updateMatch(@RequestBody matchPatchRequest: MatchPatchRequest, @PathVariable id: Long): ResponseEntity<MatchResponse> {
        val currentUser = SecurityContextHolder.getContext().authentication?.principal
        val currentUsername = if (currentUser is UserDetails) currentUser.username else currentUser.toString()
        val response = matchingService.updateMatch(currentUsername, displayResults, id, matchPatchRequest.action )
        return ResponseEntity.ok(response)
    }
}