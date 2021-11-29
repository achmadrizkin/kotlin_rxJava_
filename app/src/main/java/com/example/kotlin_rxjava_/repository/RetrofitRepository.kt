package com.example.kotlin_rxjava_.repository

import androidx.lifecycle.MutableLiveData
import com.example.kotlin_rxjava_.model.BookList
import com.example.kotlin_rxjava_.network.RetrofitService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RetrofitRepository @Inject constructor(private val retroInstance: RetrofitService) {

    fun getBookFromApiCall(name_product: String, bookList: MutableLiveData<BookList>) {
        retroInstance.getBookList(name_product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getBookListObserverRx(bookList))
    }

    private fun getBookListObserverRx(bookList: MutableLiveData<BookList>): Observer<BookList> {
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