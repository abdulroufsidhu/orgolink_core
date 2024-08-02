package io.github.abdulroufsidhu.ambaar.apis.core.auth.tokenizer

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
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
    fun isValid(token: String, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)
        return userDetails.username == email && !isExpired(token)
    }
    @Throws(ExpiredJwtException::class)
    fun extractEmail(token: String): String? =
        getAllClaims(token)
            .subject

    @Throws(ExpiredJwtException::class)
    fun isExpired(token: String): Boolean =
        getAllClaims(token)
            .expiration
            .before(Date(System.currentTimeMillis()))

    @Throws(ExpiredJwtException::class)
    private fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()
        return parser
            .parseSignedClaims(token)
            .payload
    }
}