package com.chernorotov.gb06_popular_library_git_client.data.room.mappers

import com.chernorotov.gb06_popular_library_git_client.data.room.model.UserEntity
import com.chernorotov.gb06_popular_library_git_client.domain.model.User

class UserEntityMapper : EntityMapper<UserEntity, User> {
    override fun mapToDomain(entity: UserEntity) = User(
        entity.id,
        entity.login,
        entity.avatarUrl
    )
}