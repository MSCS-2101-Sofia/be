package org.tennismate.com.common.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long>{
    fun findByName(username: String): List<User>

    @Query(
        value =
        """
                select * from users limit 10;
            """,
        nativeQuery = true
    )
    fun findUserMatchingAvailableTimeRange(): List<User>
}