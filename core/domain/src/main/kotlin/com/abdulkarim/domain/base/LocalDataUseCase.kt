package com.abdulkarim.domain.base

interface UseCase

interface CoroutineUseCase<Params, Type>: UseCase {
    suspend fun execute(params: Params? = null):Type
}

interface RoomUseCaseNonParams<Type> : UseCase {
    suspend fun execute(): Type
}