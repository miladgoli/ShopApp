package com.example.shopapp.model.repository.user

import com.example.shopapp.model.database.user.UserDao
import com.example.shopapp.model.entitys.User
import io.reactivex.Completable
import io.reactivex.Single

class UserRepositoryImp(val userDao: UserDao) : UserRepository {

    override fun addUser(user: User): Completable {
        return userDao.addUser(user)
    }

    override fun updateUser(user: User): Completable {
        return userDao.updateUser(user)
    }

    override fun deleteUser(user: User): Completable {
        return userDao.deleteUser(user)
    }

    override fun getUsers(): Single<List<User>> {
        return userDao.getAllUser()
    }

    override fun searchInUsersByUsername(query: String): Single<User?> {
        return userDao.searchInUsersByUsername(query)
    }

    override fun searchInUsersByEmail(email: String): Single<User?> {
        return userDao.searchInUsersByEmail(email)
    }

    override fun deleteAllUsers(): Completable {
        return userDao.deleteAllUsers()
    }

}