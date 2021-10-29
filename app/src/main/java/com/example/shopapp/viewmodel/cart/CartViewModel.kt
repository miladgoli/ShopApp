package com.example.shopapp.viewmodel.cart

import com.example.shopapp.model.repository.products.ProductRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.model.entitys.Cart
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.repository.cart.CartRepository
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CartViewModel(repository: CartRepository) : ViewModel() {

    val repository: CartRepository = repository

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    //MutableLiveData
    val errorsMu: MutableLiveData<String> = MutableLiveData<String>()
    val addCartMu: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val getCartMu: MutableLiveData<List<Cart>> = MutableLiveData<List<Cart>>()
    val deleteCartMu: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val deleteAllCartMu: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val updateCartMu: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


    fun addCart(cart: Cart) {
        repository.addCart(cart)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    addCartMu.postValue(true)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun deleteCart(cart: Cart) {
        repository.deleteCart(cart)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    deleteCartMu.postValue(true)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun updateCart(cart: Cart) {
        repository.updateCart(cart)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    updateCartMu.postValue(true)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun deleteAllProduct() {
        repository.deleteAllCart()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    deleteAllCartMu.postValue(true)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun getAllCarts() {
        repository.getCart()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Cart>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Cart>) {
                    getCartMu.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }
            })
    }

        //return mutable
        fun getCarts(): LiveData<List<Cart>> {
            return getCartMu

        }

        fun getErrors(): LiveData<String> {
            return errorsMu
        }

        fun getAddCart(): LiveData<Boolean> {
            return addCartMu
        }

        fun getProduct(): LiveData<Boolean> {
            return addCartMu
        }

        fun getDeleteProduct(): LiveData<Boolean> {
            return deleteCartMu
        }

        fun getDeleteAllProduct(): LiveData<Boolean> {
            return deleteAllCartMu
        }

        fun getUpdateAllProduct(): LiveData<Boolean> {
            return updateCartMu
        }


        override
        fun onCleared() {
            compositeDisposable.dispose()
            compositeDisposable.clear()
            super.onCleared()
        }
    }