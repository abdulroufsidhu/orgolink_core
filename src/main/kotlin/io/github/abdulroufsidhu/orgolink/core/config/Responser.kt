package io.github.abdulroufsidhu.orgolink.core.config

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import java.util.concurrent.CompletableFuture


data class ResponseObj<A>(
    val code: Int,
    val message: String,
    val data: A,
)

sealed class Responser<T>(body: T, headers: HttpHeaders, status: HttpStatus) :
    ResponseEntity<T>(body, headers, status) {

    companion object {
        @Async
        fun <T> success(
            headers: Map<String, String> = mapOf(),
            data: () -> T
        ): CompletableFuture<Responser<ResponseObj<T>>> {
            val h = HttpHeaders()
            headers.forEach { (k, v) -> h.add(k, v) }
            val d = data()
            return CompletableFuture.completedFuture(
                Success(
                    _headers = h,
                    ResponseObj(
                        HttpStatus.OK.value(),
                        "success",
                        d
                    )
                ) as Responser<ResponseObj<T>>
            )
        }

        fun <T> error(error: () -> T) =
            Error(
                _headers = HttpHeaders(),
                ResponseObj(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "error",
                    error()
                )
            ) as Responser<ResponseObj<T>>
    }

    private data class Success<S>(val _headers: HttpHeaders, val data: S) :
        Responser<S>(data, _headers, HttpStatus.OK)

    private data class Error<E>(val _headers: HttpHeaders, val error: E) :
        Responser<E>(error, _headers, HttpStatus.INTERNAL_SERVER_ERROR)

}