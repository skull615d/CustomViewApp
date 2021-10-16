package me.igorfedorov.newsfeedapp.common

sealed class Either<out L, out R> {

    data class Left<out L>(val failure: L) : Either<L, Nothing>()

    data class Right<out R>(val success: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>

    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Either.Left(a)

    fun <R> right(b: R) = Either.Right(b)

    fun fold(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Left -> fnL(failure)
            is Right -> fnR(success)
        }
}

fun <L, R> Either<L, R>.onFailure(action: (failure: L) -> Unit): Either<L, R> =
    this.apply { if (this is Either.Left) action(failure) }

fun <L, R> Either<L, R>.onSuccess(action: (success: R) -> Unit): Either<L, R> =
    this.apply { if (this is Either.Right) action(success) }