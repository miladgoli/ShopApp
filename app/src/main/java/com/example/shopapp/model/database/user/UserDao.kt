package com.example.shopapp.model.database.user

import androidx.room.*
import com.example.shopapp.model.entitys.User
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User): Completable

    @Update
    fun updateUser(user: User): Completable

    @Query("select * from tbl_user")
    fun getAllUser(): Single<List<User>>

    @Delete
    fun deleteUser(user: User): Completable

    /**
     * this function takes a username for searching in database.
     * if username has already exists @return User and if this
     * username didn't exist so @return null
     */
    @Query("SELECT * FROM tbl_user WHERE username == :usernameString")
     fun searchInUsersByUsername(usernameString: String): Single<User?>

    /**
     * this function takes a email for searching in database.
     * if email has already exists @return User and if this
     * email didn't exist so @return null
     */
    @Query("SELECT * FROM tbl_user WHERE email == :email")
     fun searchInUsersByEmail(email: String): Single<User?>

    @Query("delete from tbl_user")
    fun deleteAllUsers(): Completable

}