package com.chernorotov.gb06_popular_library_git_client.data.room.mappers

interface EntityMapper<E, D> {
    fun mapToDomain(entity: E): D
}