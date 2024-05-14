package org.tennismate.com.common.data

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    var username: String,
    var passwordMD5: String,
    var gender: String? = null,
    var tennisLevel: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var addressLine1: String? = null,
    var addressLine2: String? = null,
    var zipCode: String? = null,
): Serializable {
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