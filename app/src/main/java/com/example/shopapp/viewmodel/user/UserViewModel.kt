package com.example.shopapp.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.entitys.User
import com.example.shopapp.model.repository.user.UserRepository
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserViewModel(val repository: UserRepository) : ViewModel() {

    val compositeDisposable:CompositeDisposable= CompositeDisposable()

    //MutableLiveData
    val errorsMu: MutableLiveData<String> = MutableLiveData()

    private val addUserMu: MutableLiveData<Boolean> = MutableLiveData()
    private val getUsersMu: MutableLiveData<List<User>> = MutableLiveData()
    private val searchInUserByUsernameMu:MutableLiveData<User> = MutableLiveData()
    private val searchInUserByEmailMu:MutableLiveData<User> = MutableLiveData()
    private val deleteUserMu:MutableLiveData<Boolean> = MutableLiveData()
    private val deleteAllUserMu:MutableLiveData<Boolean> = MutableLiveData()
    private val updateUserMu: MutableLiveData<Boolean> = MutableLiveData()



    fun addUser(user: User){
       repository.addUser(user)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(object : CompletableObserver{
               override fun onSubscribe(d: Disposable) {
                   compositeDisposable.add(d)
               }

               override fun onComplete() {
                   addUserMu.postValue(true)
               }

               override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
               }

           })
    }

    fun deleteUser(user: User){
        repository.deleteUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    deleteUserMu.postValue(true)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun updateUser(user: User){
        repository.updateUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    updateUserMu.postValue(true)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun deleteAllUsers(){
       repository.deleteAllUsers()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(object : CompletableObserver{
               override fun onSubscribe(d: Disposable) {
                   compositeDisposable.add(d)
               }

               override fun onComplete() {
                   deleteAllUserMu.postValue(true)
               }

               override fun onError(e: Throwable) {
                   errorsMu.postValue(e.toString())
               }

           })
    }

    fun getUsersMethod(){
        repository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<User>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<User>) {
                    getUsersMu.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun searchInUserByUsername(query:String){
        repository.searchInUsersByUsername(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<User?>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: User) {
                    searchInUserByUsernameMu.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun searchInUserByEmail(query:String){
        repository.searchInUsersByEmail(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<User?>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: User) {
                    searchInUserByEmailMu.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    //return mutable

    fun getUsers(): LiveData<List<User>>{
        return getUsersMu
    }

    fun getSearchInUserByEmail(): LiveData<User>{
        return getSearchInUserByEmail()
    }

    fun getSearchInUserByUsername(): LiveData<User>{
        return getSearchInUserByUsername()
    }

}