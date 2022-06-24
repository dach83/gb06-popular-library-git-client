package com.chernorotov.gb06_popular_library_git_client.data.retrofit.mappers

interface DtoMapper<E, D> {
    fun mapToDomain(dtoEntity: E): D
}