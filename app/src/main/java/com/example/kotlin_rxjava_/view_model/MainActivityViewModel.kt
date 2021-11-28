package com.example.kotlin_rxjava_.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_rxjava_.model.BookList
import com.example.kotlin_rxjava_.network.RetrofitInstance
import com.example.kotlin_rxjava_.network.RetrofitService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel: ViewModel() {
    lateinit var bookList: MutableLiveData<BookList>

    init {
        bookList = MutableLiveData()
    }

    fun getBookListObservable(): MutableLiveData<BookList> {
        return bookList
    }

    fun getBookFromApiCall(name_product: String) {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        retrofitInstance.getBookList(name_product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getBookListObserverRx())
    }

    private fun getBookListObserverRx(): Observer<BookList> {
        return object : Observer<BookList> {
            override fun onSubscribe(d: Disposable) {
                // start progress indicator
            }

            override fun onNext(t: BookList) {
                bookList.postValue(t)
            }

            override fun onError(e: Throwable) {
                bookList.postValue(null)
            }

            override fun onComplete() {
                // end progress indicator
            }
        }
    }
}