package org.tennismate.com.common.data

import javax.persistence.*

@Entity
@Table(name = "tokens")
data class Token(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val token: String,
    @ManyToOne
    val user: User
)
