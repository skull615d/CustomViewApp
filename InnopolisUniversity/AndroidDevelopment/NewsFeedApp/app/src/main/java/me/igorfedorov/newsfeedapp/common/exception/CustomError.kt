package me.igorfedorov.newsfeedapp.common.exception

sealed class CustomError(val message: String? = null) {
    class NetworkConnection(message: String? = null) : CustomError(message = message)
    class ServerError(message: String? = null) : CustomError(message = message)

    // for feature specific failures
    abstract class FeatureSpecificError(message: String? = null) : CustomError(message = message)
}