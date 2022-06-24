package com.chernorotov.gb06_popular_library_git_client.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chernorotov.gb06_popular_library_git_client.data.room.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}