package org.tennismate.com.common.data

import org.springframework.data.jpa.repository.JpaRepository

interface TokenRepository : JpaRepository<Token, Long>
