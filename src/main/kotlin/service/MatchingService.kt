package org.tennismate.com.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.tennismate.com.common.data.*
import org.tennismate.com.controller.MatchingController.MatchResponse

@Service
class MatchingService @Autowired constructor(
    private val matchRepository: MatchRepository,
    private val userRepository: UserRepository
) {
    fun getMatch(user: User): List<MatchResponse>{
        val res = mutableListOf<MatchResponse>()
        val currentMatches = matchRepository.findByUsername1(user.username)
        currentMatches.forEach { match ->
            val username2 = match.username2
            val user2 = userRepository.findByUsername(username2)!!

            val matchStatus: MatchStatus = if(match.sender == 0) {
                when(match.receiverResponse) {
                    MatchAction.None -> MatchStatus.WAITING_FOR_APPROVAL
                    MatchAction.APPROVE_REQUEST -> MatchStatus.APPROVED
                    MatchAction.DECLINE_REQUEST -> MatchStatus.DECLINED_BY_OTHER_USER
                    else -> MatchStatus.NONE
                }
            } else {
                when(match.receiverResponse) {
                    MatchAction.None -> MatchStatus.REQUESTED
                    MatchAction.APPROVE_REQUEST -> MatchStatus.APPROVED
                    MatchAction.DECLINE_REQUEST -> MatchStatus.DECLINED
                    else -> MatchStatus.NONE
                }
            }
            val showPhoneNumber = if(matchStatus == MatchStatus.APPROVED) user2.phoneNumber else "Hidden"
            res.add(MatchResponse(
                user2.id,
                user2.username,
                user2.city,
                user2.tennisLevel,
                user2.gender,
                showPhoneNumber,
                matchStatus
            ))
        }

        //add 10 more candidate users
        userRepository.findCandidateUsers(user.username).forEach { candidate ->
            res.add(
                MatchResponse(
                    candidate.id,
                    candidate.username,
                    candidate.city,
                    candidate.tennisLevel,
                    candidate.gender,
                    "Hidden",
                    MatchStatus.NONE
            ))
        }
        return res
    }

    fun updateMatch(username1: String, displayResults: List<MatchResponse>, id: Long, matchAction: MatchAction): MatchResponse {
        var entry = displayResults.filter { it -> it.id == id}[0]
        entry.matchStatus = updateMatchStatus(entry.matchStatus, matchAction, username1, entry.username)
        return entry
    }

    private fun updateMatchStatus(matchStatus: MatchStatus, matchAction: MatchAction, username1: String, username2: String): MatchStatus{
        if(matchStatus == MatchStatus.NONE && matchAction == MatchAction.SEND_REQUEST) {
            matchRepository.save(Match(
                username1,
                username2,
                sender = 0,
                receiverResponse = MatchAction.None
            ))
            matchRepository.save(Match(
                username2,
                username1,
                sender = 1,
                receiverResponse = MatchAction.None
            ))
            return MatchStatus.WAITING_FOR_APPROVAL
        }

        if(matchStatus == MatchStatus.REQUESTED && matchAction == MatchAction.APPROVE_REQUEST) {
            val match1 = matchRepository.findByUsername1AndUsername2(username1, username2)
            val match2 = matchRepository.findByUsername1AndUsername2(username2, username1)
            match1.receiverResponse = MatchAction.APPROVE_REQUEST
            match2.receiverResponse = MatchAction.APPROVE_REQUEST
            matchRepository.saveAndFlush(match1)
            matchRepository.saveAndFlush(match2)
            return MatchStatus.APPROVED
        }

        if(matchStatus == MatchStatus.REQUESTED && matchAction == MatchAction.DECLINE_REQUEST) {
            val match1 = matchRepository.findByUsername1AndUsername2(username1, username2)
            val match2 = matchRepository.findByUsername1AndUsername2(username2, username1)
            match1.receiverResponse = MatchAction.DECLINE_REQUEST
            match2.receiverResponse = MatchAction.DECLINE_REQUEST
            matchRepository.saveAndFlush(match1)
            matchRepository.saveAndFlush(match2)
            return MatchStatus.DECLINED
        }
        throw Exception(
            "Not possible combination"
        )
    }
}