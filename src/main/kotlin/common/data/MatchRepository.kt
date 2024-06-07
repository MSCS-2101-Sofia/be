package org.tennismate.com.common.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MatchRepository: JpaRepository<Match, Long> {
    fun findByUsername1(username: String): List<Match>
    fun findByUsername1AndUsername2(username1: String, username2: String): Match

    @Query(
        value =
        """
          select * from users where id not in 
          (select username2 from matches where username1 = :username) limit 10;
        """,
        nativeQuery = true
    )
    fun hasMatchRequest()
}