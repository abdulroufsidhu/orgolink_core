package io.github.abdulroufsidhu.ambaar.apis.core

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class ResponseObj<A>(
    val code: Int,
    val message: String,
    val data: A,
)

sealed class Responser<T>(body: T, status: HttpStatus) : ResponseEntity<T>(body, status) {

    companion object {
        fun <T> success(data: () -> T) =
            Success(
                ResponseObj(
                    HttpStatus.OK.value(),
                    "success",
                    data()
                )
            ) as Responser<ResponseObj<T>>

        fun <T> error(error: () -> T) =
            Error(
                ResponseObj(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "error",
                    error()
                )
            ) as Responser<ResponseObj<T>>
    }

    private data class Success<S>(val data: S) : Responser<S>(data,HttpStatus.OK)

    private data class Error<E>(val error: E) : Responser<E>(error,HttpStatus.INTERNAL_SERVER_ERROR)

}