package io.github.abdulroufsidhu.ambaar.apis.base.core.auth.tokenizer

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpSession
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date

@Service
class TokenService(
    private val jwtProperties: JwtProperties
) {
    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray()
    )

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun generate(
        userDetails: UserDetails,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String =
        Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration))
            .add(additionalClaims)
            .and()
            .signWith(secretKey)
            .compact()

    @Cacheable(value = ["tokenValidity"], key = "#tokenToValidate")
    fun isValid( tokenToValidate: String, userDetails: UserDetails): Boolean {
        logger.info("validating token")
        val email = extractEmail(tokenToValidate)
        return userDetails.username == email && !isExpired(tokenToValidate)
    }

    @Cacheable(value = ["tokenEmail"], key = "#tokenToExtractEmail")
    @Throws(ExpiredJwtException::class)
    fun extractEmail(tokenToExtractEmail: String): String? =
        getAllClaims(tokenToExtractEmail)
            .subject

    @Throws(ExpiredJwtException::class)
    fun isExpired(token: String): Boolean =
        getAllClaims(token)
            .expiration
            .before(Date(System.currentTimeMillis()))


    @Cacheable(value = ["tokenClaims"], key = "#tokenToExtractClaims")
    @Throws(ExpiredJwtException::class)
    private fun getAllClaims(tokenToExtractClaims: String): Claims {
        logger.info("extracting user from token")
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()
        return parser
            .parseSignedClaims(tokenToExtractClaims)
            .payload
    }
}