package me.igorfedorov.newsfeedapp.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Either<T>(data: T) : Resource<T>(data = data)
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
}
