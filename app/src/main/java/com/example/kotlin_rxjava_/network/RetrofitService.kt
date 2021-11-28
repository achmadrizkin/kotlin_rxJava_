package com.example.kotlin_rxjava_.network

import com.example.kotlin_rxjava_.model.BookList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("np/{name_product}")
    fun getBookList(@Path("name_product") name_product: String): Observable<BookList>
}