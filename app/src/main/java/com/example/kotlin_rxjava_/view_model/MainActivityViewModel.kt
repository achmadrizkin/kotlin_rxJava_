package com.example.kotlin_rxjava_.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_rxjava_.model.BookList
import com.example.kotlin_rxjava_.network.RetrofitService
import com.example.kotlin_rxjava_.repository.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RetrofitRepository): ViewModel() {
    lateinit var bookList: MutableLiveData<BookList>

    init {
        bookList = MutableLiveData()
    }

    fun getBookListObservable(): MutableLiveData<BookList> {
        return bookList
    }

    fun getBookListOfData(name_product: String) {
        repository.getBookFromApiCall(name_product, bookList)
    }
}