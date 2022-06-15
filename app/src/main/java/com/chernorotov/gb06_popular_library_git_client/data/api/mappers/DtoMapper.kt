package com.chernorotov.gb06_popular_library_git_client.data.api.mappers

interface DtoMapper<E, D> {
    fun mapToDomain(dtoEntity: E): D
}