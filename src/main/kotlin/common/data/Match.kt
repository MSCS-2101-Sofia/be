package org.tennismate.com.common.data

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "matches")
data class Match(
    var username1: String,
    var username2: String,
    var sender: Int,
    var receiverResponse: MatchAction = MatchAction.None
) {
    @CreationTimestamp
    @Column(updatable = false, insertable = false)
    var createdAt: LocalDateTime? = null
    @UpdateTimestamp
    @Column(insertable = false)
    var updatedAt: LocalDateTime? = null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}