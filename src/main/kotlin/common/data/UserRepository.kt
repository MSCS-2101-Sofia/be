package org.tennismate.com.common.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long>{
    fun findByUsername(username: String): User?

    @Query(
        value =
        """
          select * from users where username not in 
          (select username2 from matches where username1 = :username) and username != :username limit 10;
        """,
        nativeQuery = true
    )
    fun findCandidateUsers(username: String): List<User>
}