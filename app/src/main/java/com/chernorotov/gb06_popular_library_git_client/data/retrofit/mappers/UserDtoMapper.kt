package com.chernorotov.gb06_popular_library_git_client.data.retrofit.mappers

import com.chernorotov.gb06_popular_library_git_client.data.retrofit.model.UserDto
import com.chernorotov.gb06_popular_library_git_client.domain.model.User

class UserDtoMapper : DtoMapper<UserDto, User> {
    override fun mapToDomain(dto: UserDto) = User(
        dto.id,
        dto.login,
        dto.avatarUrl.orEmpty(),
    )
}