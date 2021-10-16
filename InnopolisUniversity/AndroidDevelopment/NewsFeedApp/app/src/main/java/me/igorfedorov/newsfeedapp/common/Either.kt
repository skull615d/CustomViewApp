package me.igorfedorov.newsfeedapp.common

sealed class Either<out L, out R> {

    data class Failure<out L>(val failure: L) : Either<L, Nothing>()

    data class Success<out R>(val success: R) : Either<Nothing, R>()

    val isRight get() = this is Success<R>

    val isLeft get() = this is Failure<L>

    fun <L> failure(failure: L) = Failure(failure)

    fun <R> success(success: R) = Success(success)

    fun fold(fnFailure: (L) -> Any, fnSuccess: (R) -> Any): Any =
        when (this) {
            is Failure -> fnFailure(failure)
            is Success -> fnSuccess(success)
        }
}

fun <L, R> Either<L, R>.onFailure(action: (failure: L) -> Unit): Either<L, R> =
    this.apply { if (this is Either.Failure) action(failure) }

fun <L, R> Either<L, R>.onSuccess(action: (success: R) -> Unit): Either<L, R> =
    this.apply { if (this is Either.Success) action(success) }