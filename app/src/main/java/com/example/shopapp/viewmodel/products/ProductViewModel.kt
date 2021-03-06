package com.example.shopapp.viewmodel.products

import com.example.shopapp.model.repository.products.ProductRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.model.entitys.Product
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductViewModel(repository: ProductRepository) : ViewModel() {

    val repository: ProductRepository =repository

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    //MutableLiveData
    val errorsMu: MutableLiveData<String> = MutableLiveData<String>()
    val addProductMu: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val getProductMu: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    val getSearchProductMu: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    val deleteProductMu: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val deleteAllProductMu: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val updateProductMu: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


    fun addProduct(product: Product){
        repository.addProduct(product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    addProductMu.postValue(true)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun searchProduct(search:String){
        repository.searchProduct(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Product>>{
                override fun onSubscribe(d: Disposable) {
                   compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Product>) {
                    getSearchProductMu.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun deleteProduct(product: Product){
        repository.deleteProduct(product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    deleteProductMu.postValue(true)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun updateProduct(product: Product){
        repository.updateProduct(product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    deleteProductMu.postValue(true)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun deleteAllProduct(){
        repository.deleteAllProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    deleteAllProductMu.postValue(true)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun getAllProducts() {
        repository.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Product>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Product>) {
                    getProductMu.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }


    //return mutable
    fun getProducts(): LiveData<List<Product>>{
        return getProductMu

    }

    fun getSearchedProducts(): LiveData<List<Product>>{
        return getSearchProductMu

    }

    fun getErrors():LiveData<String> {
        return errorsMu
    }

    fun getAddProduct():LiveData<Boolean>{
        return addProductMu
    }

    fun getProduct():LiveData<Boolean>{
        return addProductMu
    }

    fun getDeleteProduct():LiveData<Boolean>{
        return deleteProductMu
    }

    fun getDeleteAllProduct():LiveData<Boolean>{
        return deleteAllProductMu
    }

    fun getUpdateAllProduct():LiveData<Boolean>{
        return updateProductMu
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }
}