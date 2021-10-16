package me.igorfedorov.newsfeedapp.common.exception

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()

    // for feature specific failures
    abstract class FeatureSpecificFailure : Failure()
}