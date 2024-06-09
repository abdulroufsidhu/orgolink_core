package io.github.abdulroufsidhu.ambaar.core

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

sealed class Responser<T>(status: HttpStatus) : ResponseEntity<T>(status) {

    companion object {
        fun <T> success(data: () -> T): Responser<ResponseObj<T>> =
            Success(ResponseObj(HttpStatus.OK.value(), "success", data()))

        fun <T> error(error: () -> T): Responser<ResponseObj<T>> =
            Error(ResponseObj(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", error()))
    }

    data class ResponseObj<A>(
        val code: Int,
        val message: String,
        val data: A,
    )

    private data class Success<S>(val data: S) : Responser<S>(HttpStatus.OK)

    private data class Error<E>(val error: E) : Responser<E>(HttpStatus.INTERNAL_SERVER_ERROR)

}