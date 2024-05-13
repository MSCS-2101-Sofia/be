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
    var gender: String,
    var email: String? = null,
    var phone: String,
    var addressLine1: String,
    var addressLine2: String? = null,
    var zipCode: String,
    var passwordMD5: String
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