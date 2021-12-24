package com.example.shopapp.model.repository.user

import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.entitys.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {

     fun addUser(user: User): Completable

     fun updateUser(user: User): Completable

     fun deleteUser(user: User): Completable

     fun getUsers(): Single<List<User>>

     fun searchInUsersByUsername(query: String): Single<User?>

     fun searchInUsersByEmail(email: String): Single<User?>

     fun deleteAllUsers(): Completable

}