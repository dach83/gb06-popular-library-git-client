package com.chernorotov.gb06_popular_library_git_client.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.chernorotov.gb06_popular_library_git_client.data.room.model.UserEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): Single<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUser(userId: Int): Single<UserEntity>

    @Insert(onConflict = REPLACE)
    fun insert(users: List<UserEntity>)

}